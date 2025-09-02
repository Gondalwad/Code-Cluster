package com.CodeCluster.UserService.mapper;

import com.CodeCluster.UserService.dto.UserRequestDTO;
import com.CodeCluster.UserService.model.User;

import java.time.LocalDate;

public class UserRequestDTOToUser {

    public static User convert(UserRequestDTO userRequestDTO){
        User user;
        user = new User();
        user.setName(userRequestDTO.getName());
        user.setUsername(userRequestDTO.getUserName());
        user.setPassword(userRequestDTO.getPassword());
        user.setEmail(userRequestDTO.getEmail());
        user.setStatus("Active");
        user.setRole("User");
        user.setRegistrationDate(LocalDate.now());
        return user;
    }
}
