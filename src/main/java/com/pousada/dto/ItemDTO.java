package com.pousada.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDTO {

    private Long id;

    private String descricao;

    private String tipo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
}
