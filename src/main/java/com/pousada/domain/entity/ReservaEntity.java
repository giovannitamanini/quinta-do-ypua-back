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
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo_reserva", nullable = false, unique = true, length = 20)
    private String codigoReserva;

    @ManyToOne
    @JoinColumn(name = "id_hospede", nullable = false)
    private HospedeEntity hospede;

    @ManyToOne
    @JoinColumn(name = "id_acomodacao", nullable = false)
    private AcomodacaoEntity acomodacao;

    @Column(name = "data_inicial", nullable = false)
    private LocalDate dataInicial;

    @Column(name = "qtd_diarias", nullable = false)
    private Integer qtdDiarias;

    @Column(name = "valor_diaria", nullable = false)
    private BigDecimal valorDiaria;

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
