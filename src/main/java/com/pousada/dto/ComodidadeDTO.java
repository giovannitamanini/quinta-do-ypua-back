package com.pousada.dto;

import com.pousada.enums.TipoComodidadeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComodidadeDTO {

    private Long id;

    private String descricao;

    private TipoComodidadeEnum tipo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}
