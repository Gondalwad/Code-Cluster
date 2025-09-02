package com.CodeCluster.UserService.dto;

public class UserResponseDTO {
    private final String userName;

    public UserResponseDTO(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
