package com.pousada.dto;

import com.pousada.enums.StatusPagamentoEnum;
import com.pousada.enums.StatusReservaEnum;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservaDTO {

    private Long id;

    private StatusReservaEnum statusReserva = StatusReservaEnum.PENDENTE;

    private LocalDateTime dataHora = LocalDateTime.now();

    private LocalDate checkIn;

    private LocalDate checkOut;

    private Integer numeroPernoites;

    private Double custo;

    private StatusPagamentoEnum statusPagamento = StatusPagamentoEnum.PENDENTE;

    private Integer idAcomodacao;

    private Long idHospede;

}
