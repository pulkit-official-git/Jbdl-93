package com.example.b93Rest.dtos;

import com.example.b93Rest.model.Gender;
import com.example.b93Rest.model.Status;
import com.example.b93Rest.model.User;

import java.util.Date;

public class CreateUserRequestDto {

    private String name;

    private String mobile;

    private String email;

    private Gender gender;

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

    public User dtoToModel(){
        User user = new User();
        user.setName(this.getName());
        user.setEmail(this.getEmail());
        user.setGender(this.getGender());
        user.setStatus(Status.ACTIVE);
        user.setMobile(this.getMobile());
        user.setCreatedOn(new Date());
        user.setUpdatedOn(new Date());
        return user;
    }
}
