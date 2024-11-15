package com.pousada.service;

import com.pousada.dto.SignupRequest;
import com.pousada.domain.entity.UsuarioEntity;

public interface AuthService {
    UsuarioEntity createCustomer(SignupRequest signupRequest);
}