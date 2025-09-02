package com.CodeCluster.AuthService.dto;

public class UserResponseDTO {
    private final String jwt;

    public UserResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt(){
        return this.jwt;
    }
}
