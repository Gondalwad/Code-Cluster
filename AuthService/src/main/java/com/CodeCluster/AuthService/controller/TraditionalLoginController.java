package com.CodeCluster.AuthService.controller;


import com.CodeCluster.AuthService.dto.LoginRequestDTO;
import com.CodeCluster.AuthService.dto.LoginResponseDTO;
import com.CodeCluster.AuthService.service.TraditionalAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
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

    @GetMapping("/validate")
    public ResponseEntity<Void> validate(@RequestHeader("Authorization") String authHeader){
        // Authorization: Bearer <token>
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
