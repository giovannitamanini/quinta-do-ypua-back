package com.pousada.service;

import com.pousada.domain.entity.UsuarioEntity;
import com.pousada.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usuario)
            throws UsernameNotFoundException {
        UsuarioEntity user = userRepository.findByUsuario(usuario);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new MyUserDetails(user);
    }

}