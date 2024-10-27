package com.pousada.dto;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.ItemEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AcomodacaoItemDTO {

    private Long id;

    private AcomodacaoEntity acomodacao;

    private ItemEntity item;

    private Integer quantidadeItem;

    private LocalDateTime dataAdicionado;
}
