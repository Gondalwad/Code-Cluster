package com.CodeCluster.UserService.controller;

import com.CodeCluster.UserService.dto.UserRequestDTO;
import com.CodeCluster.UserService.dto.UserResponseDTO;
import com.CodeCluster.UserService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){

        Optional<UserResponseDTO> createdUser = userService.createUser(userRequestDTO);

        if(createdUser.isEmpty()){
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(createdUser.get());


    }
}
