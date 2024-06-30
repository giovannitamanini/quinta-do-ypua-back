package com.pousada.dto;

import com.pousada.enums.StatusPagamentoEnum;
import com.pousada.enums.StatusReservaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaDTO {

    private Long id;

    private Long idHospede;

    private Integer idAcomodacao;

    private LocalDate dataInicial;

    private Integer qtdDiarias;

    @Enumerated(EnumType.STRING)
    private StatusReservaEnum statusReserva;

    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum statusPagamento;

    private LocalDate dataCheckIn;

    private LocalDate dataCheckOut;

    private Double valorTotal;

}
