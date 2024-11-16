package com.pousada.domain.repository;

import com.pousada.domain.entity.AcomodacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AcomodacaoRepository extends JpaRepository<AcomodacaoEntity, Integer>, JpaSpecificationExecutor<AcomodacaoEntity> {

    List<AcomodacaoEntity> findAll();

    List<AcomodacaoEntity> findByNomeContainingIgnoreCase(String nome);

    List<AcomodacaoEntity> findByQtdHospedes(Integer hospedes);

    List<AcomodacaoEntity> findByValorDiariaGreaterThanEqual(Double diariaMin);

    List<AcomodacaoEntity> findByValorDiariaLessThanEqual(Double diariaMax);

}
