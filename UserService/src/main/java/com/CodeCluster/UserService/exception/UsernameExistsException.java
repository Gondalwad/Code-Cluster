package com.CodeCluster.UserService.exception;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String s) {
        super(s);
    }
}
