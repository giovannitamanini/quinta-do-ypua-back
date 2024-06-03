package com.pousada.domain.entity;

import com.pousada.enums.StatusPagamentoEnum;
import com.pousada.enums.StatusReservaEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name = "reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idHospede;

    private Integer idAcomodacao;

    private LocalDateTime dataHora;

    private Integer numeroPernoites; //qtdDiarias

    @Enumerated(EnumType.STRING)
    private StatusReservaEnum statusReserva;

    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum statusPagamento;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private Double valorTotal;

}
