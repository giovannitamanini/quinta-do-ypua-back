package com.pousada.domain.repository;

import com.pousada.domain.entity.HospedeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospedeRepository extends JpaRepository<HospedeEntity, Integer> {

    List<HospedeEntity> findAll();

    List<HospedeEntity> findByNome(String nome);

    Page<HospedeEntity> findByNomeContainingOrSobrenomeContaining(String nome, String sobrenome, Pageable pageable);

    Page<HospedeEntity> findByCpf(String cpf, Pageable pageable);

    @Query("SELECT h FROM HospedeEntity h WHERE (h.nome = :text OR h.sobrenome = :text) AND h.cpf >= :cpf")
    Page<HospedeEntity> buscarComFiltros(@Param("text") String text, @Param("cpf") String cpf, Pageable pageable);

}
