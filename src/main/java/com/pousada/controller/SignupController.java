package com.pousada.controller;

import com.pousada.dto.SignupRequest;
import com.pousada.domain.entity.UsuarioEntity;
import com.pousada.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;

    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        UsuarioEntity createdUsuarioEntity = authService.createCustomer(signupRequest);
        if (createdUsuarioEntity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuarioEntity);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
        }
    }

}
