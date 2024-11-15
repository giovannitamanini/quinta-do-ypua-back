package com.pousada.domain.entity;

import com.pousada.enums.StatusPagamentoEnum;
import com.pousada.enums.StatusReservaEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_hospede", nullable = false)
    private Integer idHospede;

    @Column(name = "id_acomodacao", nullable = false)
    private Integer idAcomodacao;

    @Column(name = "data_reserva", nullable = false)
    private LocalDate dataReserva;

    @Column(name = "qtd_diarias", nullable = false)
    private Integer qtdDiarias;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_reserva", nullable = false)
    private StatusReservaEnum statusReserva;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamentoEnum statusPagamento;

    @Column(name = "data_check_in")
    private LocalDate dataCheckIn;

    @Column(name = "data_check_out")
    private LocalDate dataCheckOut;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
