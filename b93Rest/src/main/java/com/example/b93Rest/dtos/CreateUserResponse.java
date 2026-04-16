package com.example.b93Rest.dtos;

import com.example.b93Rest.model.User;

import java.util.Date;

public class CreateUserResponse {

    private Integer id;

    private Date createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public static CreateUserResponse modelToDto(User user){
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setId(user.getId());
        createUserResponse.setCreatedOn(user.getCreatedOn());
        return createUserResponse;
    }
}
