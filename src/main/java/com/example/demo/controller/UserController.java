package com.example.demo.controller;

import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    ApiResponse<User> createUser(@RequestBody UserCreateRequest request){
        return ApiResponse.<User>builder()
                .code(200)
                .message("create successfully")
                .result(userService.createUser(request))
                .build();
    }
    @GetMapping
    ApiResponse<String> getUser(){
        return ApiResponse.<String>builder()
                .code(200)
                .message("hello anh em")
                .build();
    }
    @GetMapping("/{userId}")
    ApiResponse<User> getUserById(@PathVariable String userId){
        return ApiResponse.<User>builder()
                .code(200)
                .message("get sucessfully")
                .result(userService.getUserById(userId))
                .build();
    }

}
