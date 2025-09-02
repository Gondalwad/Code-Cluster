package com.CodeCluster.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email")
    private String email;
    @NotBlank
    @Size(min = 5, max = 30)
    private String userName;
    @NotBlank
    @Size(min = 6, max = 255)
    private String password;
    private byte[] profileImg;

    // Getters and Setters
    public @NotBlank @Size(min = 1, max = 50) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 1, max = 50) String name) {
        this.name = name;
    }

    public @NotBlank @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 5, max = 30) String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank @Size(min = 5, max = 30) String userName) {
        this.userName = userName;
    }

    public @NotBlank @Size(min = 6, max = 255) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 6, max = 255) String password) {
        this.password = password;
    }

    public byte[] getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(byte[] profileImg) {
        this.profileImg = profileImg;
    }
}
