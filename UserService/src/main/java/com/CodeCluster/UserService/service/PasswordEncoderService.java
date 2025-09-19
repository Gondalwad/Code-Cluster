package com.CodeCluster.UserService.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public String passwordEncoder(String password){
            return passwordEncoder.encode(password);

        }
    }


