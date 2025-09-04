package com.CodeCluster.AuthService.service;

import com.CodeCluster.AuthService.dto.LoginRequestDTO;
import com.CodeCluster.AuthService.model.User;
import com.CodeCluster.AuthService.repo.UserRepo;
import com.CodeCluster.AuthService.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraditionalAuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo repo;
    private final JwtUtil jwtUtil;

    public TraditionalAuthService(UserRepo repo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // method to authenticate LoginRequestDTO
    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO){
        String regexUsername = "^[a-zA-Z][a-zA-Z0-9_]{2,15}$"; // username regex
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // email regex

        Optional<User> principal = Optional.empty();
        /// Checks if preferred id is username
        if(loginRequestDTO.getPreferredId().matches(regexUsername)){
            principal = repo.findByUsername(loginRequestDTO.getPreferredId());
        }
        /// Checks if preferred id is email
        if(principal.isEmpty() && loginRequestDTO.getPreferredId().matches(regexEmail)){
            principal = repo.findByEmail(loginRequestDTO.getPreferredId());
        }

        /// checks and return null if user not found
        if(principal.isEmpty()){
            return Optional.empty();
        }

        /// remaining process after finding user i.e. verify password and return jwt.
        boolean isValid = passwordEncoder.matches(loginRequestDTO.getPassword(), principal.get().getPassword());
        if(isValid){
            return jwtUtil.generateToken(principal.get().getUsername(), principal.get().getRole()).describeConstable();
        }


        return Optional.empty();

    }

}
