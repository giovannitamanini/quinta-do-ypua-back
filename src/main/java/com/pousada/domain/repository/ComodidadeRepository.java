package com.pousada.domain.repository;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.ComodidadeEntity;
import com.pousada.domain.entity.HospedeEntity;
import com.pousada.enums.TipoComodidadeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComodidadeRepository extends JpaRepository<ComodidadeEntity, Integer> {

    List<ComodidadeEntity> findAll();
    Optional<ComodidadeEntity> findByDescricao(String descricao);
    Page<ComodidadeEntity> findByDescricao(String descricao, Pageable pageable);
    Page<ComodidadeEntity> findByTipo(TipoComodidadeEnum tipo, Pageable pageable);
    Page<ComodidadeEntity> findByDescricaoAndTipo(String descricao, TipoComodidadeEnum tipo, Pageable pageable);

}
