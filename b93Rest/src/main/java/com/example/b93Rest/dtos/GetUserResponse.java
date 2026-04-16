package com.example.b93Rest.dtos;

import com.example.b93Rest.model.Gender;
import com.example.b93Rest.model.Status;
import com.example.b93Rest.model.User;

public class GetUserResponse {

    private String name;

    private String mobile;

    private String email;

    private Gender gender;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static GetUserResponse modelToDto(User user){
        if(user==null)return null;
        GetUserResponse getUserResponse = new GetUserResponse();
        getUserResponse.setName(user.getName());
        getUserResponse.setEmail(user.getEmail());
        getUserResponse.setGender(user.getGender());
        getUserResponse.setMobile(user.getMobile());
        getUserResponse.setStatus(user.getStatus());
        return getUserResponse;
    }
}
