package com.pousada.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "acomodacao_item")
public class AcomodacaoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_acomodacao", nullable = false)
    private AcomodacaoEntity acomodacao;

    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false)
    private ItemEntity item;

    @Column(name = "quantidade_item", nullable = false)
    private Integer quantidadeItem;

    @Column(name = "data_adicionado", updatable = false)
    private LocalDateTime dataAdicionado;

}

