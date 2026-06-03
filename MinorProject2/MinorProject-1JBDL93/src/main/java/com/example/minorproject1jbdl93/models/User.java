package com.example.minorproject1jbdl93.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {

    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(this.authorities.name()));
        return grantedAuthorities;
    }

    @OneToOne(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Student student;

}

//Relationship between user and student & user and admin
//User Admin (1:1)
//User Student (1:1)
