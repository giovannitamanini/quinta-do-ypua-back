package com.pousada.dto;

import com.pousada.enums.TipoComodidadeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ComodidadeDTO {

    private Integer id;

    private String descricao;

    private TipoComodidadeEnum tipo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private List<AcomodacaoDTO> comodidades;

}
