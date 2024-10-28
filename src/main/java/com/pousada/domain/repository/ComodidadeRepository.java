package com.pousada.domain.repository;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.ComodidadeEntity;
import com.pousada.domain.entity.HospedeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComodidadeRepository extends CrudRepository<ComodidadeEntity, Long> {

    List<ComodidadeEntity> findAll();

//    ComodidadeEntity findByNome(String nome);

}
