package com.example.minorproject1jbdl93.configs;


import com.example.minorproject1jbdl93.models.Authority;
import com.example.minorproject1jbdl93.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Bean
    public AuthenticationManager authenticationManager(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userService);
//        daoAuthenticationProvider.setUserDetailsService(demoUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }





    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())//never do this in production
                .authorizeHttpRequests(auth->auth
                                .requestMatchers("/book/create").hasAuthority(Authority.ADMIN.name())
                                .requestMatchers("/book/**").hasAnyAuthority(Authority.STUDENT.name(),Authority.ADMIN.name())
                        .requestMatchers("/student/create").permitAll()
                                .requestMatchers(HttpMethod.GET,"student/admin/**").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/student/**").hasAuthority(Authority.STUDENT.name())
                        .requestMatchers("/admin/**").hasAuthority(Authority.ADMIN.name())
                                .requestMatchers("/txn/**").hasAuthority(Authority.STUDENT.name())
//                        .requestMatchers("/logout").permitAll()
                        .anyRequest().permitAll()
                );

//        httpSecurity.logout().logoutUrl("/logout");
                httpSecurity.formLogin(Customizer.withDefaults()); //browser
        httpSecurity.httpBasic(Customizer.withDefaults());  //postman

        return httpSecurity.build();
    }
}
