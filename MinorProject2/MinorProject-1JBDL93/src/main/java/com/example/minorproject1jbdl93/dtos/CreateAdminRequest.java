package com.example.minorproject1jbdl93.dtos;


import com.example.minorproject1jbdl93.models.Admin;
import com.example.minorproject1jbdl93.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String name;

    public Admin toAdmin(){

        return Admin.builder()
                .name(name)
                .user(User.builder()
                        .username(username)
                        .password(password)
                        .build())
                .build();
    }
}
