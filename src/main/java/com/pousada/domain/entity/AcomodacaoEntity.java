package com.pousada.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "acomodacao")
public class AcomodacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "valor_diaria", nullable = false)
    private BigDecimal valorDiaria;

    @Column(name = "qtd_hospedes", nullable = false)
    private Integer qtdHospedes;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(
        name = "acomodacao_comodidade",
        joinColumns = @JoinColumn(name = "id_acomodacao"),
        inverseJoinColumns = @JoinColumn(name = "id_comodidade")
    )
    @JsonManagedReference
    private List<ComodidadeEntity> comodidades;

}
