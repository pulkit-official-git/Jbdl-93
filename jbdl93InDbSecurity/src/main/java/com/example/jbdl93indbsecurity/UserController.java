package com.example.jbdl93indbsecurity;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    DemoUserDetailsService demoUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/user/hello")
    public String userHello(){
        return "hello user";
    }


    @GetMapping("/admin/hello")
    public String adminHello(){
        return "hello admin";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/user/signup")
    public void createUser(@RequestParam String username,
                           @RequestParam String password){

        DemoUser user = DemoUser.builder()
                .username(username)
                .password(password)
                .authorities("USER")
                .build();

        this.demoUserDetailsService.create(user);

    }


    @PostMapping("/admin/signup")
    public void createAdmin(@RequestParam String username,
                           @RequestParam String password){

        DemoUser user = DemoUser.builder()
                .username(username)
                .password(password)
                .authorities("ADMIN")
                .build();

        this.demoUserDetailsService.create(user);
    }


    @PostMapping("/login")
    public Map<String,Object> login(@RequestParam String username,
                      @RequestParam String password,
                      HttpServletRequest httpServletRequest){

        System.out.println(username+"+++++++++++");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,context);

        return Map.of("message","Login Successful");
    }

    @PostMapping("/logout")
    public Map<String,Object> logout(HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession(false);

        if(session != null){
            session.invalidate();
        }

        SecurityContextHolder.clearContext();
        return Map.of("message","Logged Out");

    }



}
