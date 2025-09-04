package com.CodeCluster.AuthService.controller;


import com.CodeCluster.AuthService.dto.LoginRequestDTO;
import com.CodeCluster.AuthService.dto.LoginResponseDTO;
import com.CodeCluster.AuthService.service.TraditionalAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TraditionalLoginController {


    private final TraditionalAuthService authService;

    public TraditionalLoginController(TraditionalAuthService authService){
        this.authService = authService;
    }


    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDTO> signIn(@RequestBody @Valid LoginRequestDTO loginRequestDTO){


        Optional<String> token = authService.authenticate(loginRequestDTO);

        return token.map(s -> ResponseEntity.ok(new LoginResponseDTO(s)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

    }
}
