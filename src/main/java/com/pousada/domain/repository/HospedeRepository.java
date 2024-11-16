package com.pousada.domain.repository;

import com.pousada.domain.entity.HospedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospedeRepository extends JpaRepository<HospedeEntity, Integer> {

    List<HospedeEntity> findAll();

    List<HospedeEntity> findByNome(String nome);

}
