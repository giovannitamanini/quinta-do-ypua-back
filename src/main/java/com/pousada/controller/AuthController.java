//package com.pousada.controller;
//
//import com.pousada.dto.UsuarioDTO;
//import com.pousada.service.AuthService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final AuthService authService;
//
//    @Operation(summary = "Cadastra um novo usuário", method = "POST")
//    @PostMapping("/signup")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO usuario) {
//        try {
//            authService.criarUsuario(usuario);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Erro ao criar usuário.");
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuario) {
//        boolean isAuthenticated = authService.login(usuario);
//        if (isAuthenticated) {
//            return ResponseEntity.ok("Login bem-sucedido");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos");
//        }
//    }
//
//}
