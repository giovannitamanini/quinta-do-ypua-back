package com.pousada.dto;

import com.pousada.domain.entity.ComodidadeEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class AcomodacaoDTO {

    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal valorDiaria;

    private Integer qtdHospedes;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private List<ComodidadeEntity> comodidades;
}
