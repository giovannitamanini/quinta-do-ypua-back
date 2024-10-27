package com.pousada.domain.repository;

import com.pousada.domain.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
}
