package com.pousada.domain.entity;

import com.pousada.enums.StatusPagamentoEnum;
import com.pousada.enums.StatusReservaEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
