package com.pousada.domain.repository;

import com.pousada.domain.entity.HospedeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospedeRepository extends CrudRepository<HospedeEntity, Long> {

    List<HospedeEntity> findAll();

    @Query(value = "SELECT * FROM hospede WHERE nome = :nome",
        countQuery = "SELECT COUNT(*) FROM hospede WHERE nome = :nome",
        nativeQuery = true)
    List<HospedeEntity> buscarHospedesPorNome(@Param("nome") String nome);

}
