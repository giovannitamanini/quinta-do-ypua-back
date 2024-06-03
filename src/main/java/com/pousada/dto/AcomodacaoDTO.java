package com.pousada.dto;

import lombok.Data;

@Data
public class AcomodacaoDTO {

    private Integer id;

    private String nome;

    private Double valorDiaria;

    private Integer quantidadeHospedes;

    private String descricaoCamas;

}
