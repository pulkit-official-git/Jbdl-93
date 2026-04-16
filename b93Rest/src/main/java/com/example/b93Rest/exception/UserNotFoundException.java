package com.example.b93Rest.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userNotFound) {
        System.out.println(userNotFound);
    }
}
