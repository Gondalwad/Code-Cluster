package com.CodeCluster.AuthService.dto;

public class UserRequestDTO {
    private String prefferedId;
    private String password;


    public String getPrefferedId() {
        return prefferedId;
    }

    public void setPrefferedId(String preferredId) {
        this.prefferedId = preferredId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
