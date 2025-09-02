package com.CodeCluster.AuthService.controller;

import com.CodeCluster.AuthService.dto.UserRequestDTO;
import com.CodeCluster.AuthService.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraditionalLoginController {

    @PostMapping("/signin")
    public ResponseEntity<UserResponseDTO> signIn(@RequestBody @Valid UserRequestDTO userRequestDTO){

    }
}
