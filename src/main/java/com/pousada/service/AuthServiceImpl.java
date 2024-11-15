package com.pousada.service;

import com.pousada.dto.SignupRequest;
import com.pousada.domain.entity.UsuarioEntity;
import com.pousada.domain.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UsuarioEntity createCustomer(SignupRequest signupRequest) {
        //Check if customer already exist
        if (usuarioRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        BeanUtils.copyProperties(signupRequest, usuarioEntity);

        //Hash the password before saving
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        usuarioEntity.setPassword(hashPassword);
        UsuarioEntity createdUsuarioEntity = usuarioRepository.save(usuarioEntity);
        usuarioEntity.setId(createdUsuarioEntity.getId());
        return usuarioEntity;
    }
}