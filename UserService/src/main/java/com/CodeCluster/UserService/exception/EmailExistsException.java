package com.CodeCluster.UserService.exception;

import jakarta.validation.constraints.NotBlank;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(@NotBlank @NotBlank String s) {
        super(s);
    }
}
