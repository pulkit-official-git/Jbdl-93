package com.example.b93Rest.controller;


import com.example.b93Rest.dtos.CreateUserRequestDto;
import com.example.b93Rest.dtos.CreateUserResponse;
import com.example.b93Rest.dtos.GetUserResponse;
import com.example.b93Rest.dtos.UpdateUserRequest;
import com.example.b93Rest.exception.UserNotFoundException;
import com.example.b93Rest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }
//    CRUD
//    post,get, put/patch, delete

    @PostMapping("/create")
    public Integer createUser(@RequestBody CreateUserRequestDto createUserRequestDto){

//        UserService userService = new UserService();
        return userService.create(createUserRequestDto).getId();

    }

    @PostMapping("/create2")
    public CreateUserResponse createUser2(@RequestBody CreateUserRequestDto createUserRequestDto){

//        UserService userService = new UserService();
        return CreateUserResponse.modelToDto(userService.create(createUserRequestDto));

    }

    @GetMapping("/get")
    public GetUserResponse getUser(@RequestParam Integer id){

//        UserService userService =  new UserService();
        return userService.get(id);

    }

    @GetMapping("/get/id/{id}")
    public GetUserResponse getUserPV(@PathVariable("id") Integer id){

//        UserService userService =  new UserService();
        return userService.get(id);

    }

//    localhost:8080/user/get/id/411656849/order/23/com/32/very/76 path variable

//    localhost:8080/user/get?id=58964171&order=23&com=32&very=76  request param

    @PutMapping("/update")
    public GetUserResponse putUser(@RequestBody UpdateUserRequest updateUserRequest,
                                   @RequestParam Integer id){
        return this.userService.putUser(updateUserRequest.dtoToModel(),id);

    }

    @PatchMapping("/patch")
    public GetUserResponse patchUser(@RequestBody UpdateUserRequest updateUserRequest,
                                   @RequestParam Integer id){
        return this.userService.patchUser(updateUserRequest.dtoToModel(),id);

    }

    @PatchMapping("/patch2")
    public GetUserResponse patchUser2(@RequestBody UpdateUserRequest updateUserRequest,
                                     @RequestParam Integer id){
        return this.userService.patchUser2(updateUserRequest.dtoToModel(),id);

    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam Integer id){
        try{
            this.userService.deleteUser(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e){

            return new ResponseEntity("user not found",HttpStatus.ACCEPTED);
        }
    }



}
