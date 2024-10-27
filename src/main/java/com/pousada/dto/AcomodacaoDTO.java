package com.pousada.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AcomodacaoDTO {

    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal valorDiaria;

    private Integer quantidadeHospedes;

    private Boolean disponivel = true;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

}
