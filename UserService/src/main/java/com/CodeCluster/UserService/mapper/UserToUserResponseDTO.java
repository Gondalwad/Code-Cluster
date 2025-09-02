package com.CodeCluster.UserService.mapper;

import com.CodeCluster.UserService.dto.UserResponseDTO;

public class UserToUserResponseDTO {

    public static UserResponseDTO convert(String userName){
        return new UserResponseDTO(userName);
    }
}
