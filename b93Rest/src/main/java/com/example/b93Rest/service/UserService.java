package com.example.b93Rest.service;

import com.example.b93Rest.dtos.CreateUserRequestDto;
import com.example.b93Rest.dtos.GetUserResponse;
import com.example.b93Rest.exception.UserNotFoundException;
import com.example.b93Rest.model.Status;
import com.example.b93Rest.model.User;
import com.example.b93Rest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;

public class UserService {

    private UserRepository userRepository;

    private ObjectMapper objectMapper=new ObjectMapper();

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User create(CreateUserRequestDto createUserRequestDto) {

//        UserRepository userRepository = new UserRepository();
        User user = createUserRequestDto.dtoToModel();
        return userRepository.create(user);



    }

    public GetUserResponse get(Integer id) {

//        UserRepository userRepository = new UserRepository();
        return GetUserResponse.modelToDto(userRepository.getUser(id));
    }

    public GetUserResponse putUser(User user, Integer id) {

        return GetUserResponse.modelToDto(this.userRepository.update(user,id));
    }


    public GetUserResponse patchUser(User user, Integer id) {

        User exisitingUser = this.userRepository.getUser(id);
        if(exisitingUser==null)return null;

        if(user.getEmail()!=null){
            exisitingUser.setEmail(user.getEmail());
        }
        if(user.getMobile()!=null){
            exisitingUser.setMobile(user.getMobile());
        }
        if(user.getGender()!=null){
            exisitingUser.setGender(user.getGender());
        }
        if(user.getName()!=null){
            exisitingUser.setName(user.getName());
        }

        exisitingUser.setUpdatedOn(new Date());

        return GetUserResponse.modelToDto(this.userRepository.update(exisitingUser,id));

    }

    public GetUserResponse patchUser2(User user, Integer id) {

        User exisitingUser = this.userRepository.getUser(id);
        if(exisitingUser==null)return null;

        HashMap<String,Object> incoming = this.objectMapper.convertValue(user,HashMap.class);
        HashMap<String,Object> existing = this.objectMapper.convertValue(exisitingUser,HashMap.class);

        for( String i : incoming.keySet()){
            if(incoming.get(i)!=null){
                existing.put(i,incoming.get(i));
            }
        }

        exisitingUser = this.objectMapper.convertValue(existing,User.class);
        exisitingUser.setUpdatedOn(new Date());

        return GetUserResponse.modelToDto(this.userRepository.update(exisitingUser,id));
    }

    public void deleteUser(Integer id) {

        User user = this.userRepository.getUser(id);
        if(user!=null){
            user.setStatus(Status.INACTIVE);
            this.userRepository.update(user,id);
        }
        else throw new UserNotFoundException("user not found");
    }
}
