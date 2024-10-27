package com.pousada.domain.repository;

import com.pousada.domain.entity.AcomodacaoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AcomodacaoRepository extends CrudRepository<AcomodacaoEntity, Integer> {

    List<AcomodacaoEntity> findAll();

    @Query(value = "SELECT valor_diaria FROM acomodacao WHERE id = :id",
            nativeQuery = true)
    BigDecimal buscarValorDiariaPorId(@Param("id") AcomodacaoEntity id);
}
