package com.pousada.domain.repository;

import com.pousada.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    public UsuarioEntity findByUsuario(String usuario);
}