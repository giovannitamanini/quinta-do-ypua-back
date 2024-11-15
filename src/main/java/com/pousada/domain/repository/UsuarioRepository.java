package com.pousada.domain.repository;

import com.pousada.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    boolean existsByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String email);
}