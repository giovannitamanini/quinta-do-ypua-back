package com.pousada.domain.repository;

import com.pousada.domain.entity.AcomodacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcomodacaoRepository extends JpaRepository<AcomodacaoEntity, Integer> {

    List<AcomodacaoEntity> findAll();

    AcomodacaoEntity findByNome(String nome);

    Page<AcomodacaoEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<AcomodacaoEntity> findByQtdHospedesEquals(Integer qtdHospedes, Pageable pageable);

    Page<AcomodacaoEntity> findByNomeContainingAndQtdHospedesEquals(String nome, Integer qtdHospedes, Pageable pageable);


}
