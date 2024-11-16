package com.pousada.controller;

import com.pousada.service.AuthService;
import com.pousada.domain.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody UsuarioEntity user) {
        // Aqui você pode adicionar validação das credenciais, como verificar no banco de dados
        if ("usuario".equals(user.getUsuario()) && "senha".equals(user.getSenha())) {
            return authService.generateToken(user.getUsuario());  // Gera o token
        } else {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
    }
}
