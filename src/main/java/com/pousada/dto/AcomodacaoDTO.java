package com.pousada.dto;

import lombok.Data;

@Data
public class AcomodacaoDTO {

    private Integer id;

    private Integer numero;

    private String tipo;

    private Double valorDiaria;

    private String descricaoHospedes;

    private String descricaoCamas;

    private String maisInformacoes;

    private String amenidades;

    private String condicoes;

}
