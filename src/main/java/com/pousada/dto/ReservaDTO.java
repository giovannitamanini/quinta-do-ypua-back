package com.pousada.dto;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.HospedeEntity;
import com.pousada.enums.StatusPagamentoEnum;
import com.pousada.enums.StatusReservaEnum;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservaDTO {

    private Long id;

    private HospedeEntity hospede;

    private AcomodacaoEntity acomodacao;

    private LocalDate dataReserva;

    private Integer qtdDiarias;

    private BigDecimal valorTotal;

    private StatusReservaEnum statusReserva;

    private StatusPagamentoEnum statusPagamento;

    private LocalDate dataCheckIn;

    private LocalDate dataCheckOut;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

}
