package com.pousada.domain.repository;

import com.pousada.domain.entity.AcomodacaoItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcomodacaoItemRepository extends CrudRepository<AcomodacaoItemEntity, Long> {

}
