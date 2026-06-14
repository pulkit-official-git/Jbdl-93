package com.example;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank
    private String name;


    @Email
    private String email;


    @NotBlank
    private String mobile;


    public User toUser(){
        return User.builder()
                .name(name)
                .email(email)
                .mobile(mobile)
                .build();
    }
}
