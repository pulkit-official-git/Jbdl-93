package com.example.b93Rest.controller;

import com.example.b93Rest.dtos.CreateUserRequestDto;
import com.example.b93Rest.dtos.CreateUserResponse;
import com.example.b93Rest.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController2 {


//    @PostMapping("/user/create")
//    public CreateUserResponse createUser2(@RequestBody CreateUserRequestDto createUserRequestDto){
//
//        UserService userService = new UserService();
//        return CreateUserResponse.modelToDto(userService.create(createUserRequestDto));
//
//    }
}
