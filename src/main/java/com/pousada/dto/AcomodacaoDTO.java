package com.pousada.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AcomodacaoDTO {

    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal valorDiaria;

    private Integer qtdHospedes;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private List<ComodidadeDTO> comodidades;
}
