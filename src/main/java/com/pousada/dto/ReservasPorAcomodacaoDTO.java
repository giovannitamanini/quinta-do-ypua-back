package com.pousada.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ReservasPorAcomodacaoDTO {

    private String acomodacaoNome;
    private int numeroReservas;
}
