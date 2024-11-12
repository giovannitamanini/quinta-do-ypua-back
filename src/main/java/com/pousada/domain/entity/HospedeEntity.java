package com.pousada.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "hospede")
@Table(name = "hospede")
public class HospedeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 45)
    private String sobrenome;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "cep", length = 10)
    private String cep;

    @Column(name = "endereco", length = 100)
    private String endereco;

    @Column(name = "cidade", length = 45)
    private String cidade;

    @Column(name = "estado", length = 45)
    private String estado;

    @Column(name = "pais", length = 45, columnDefinition = "VARCHAR(45) DEFAULT 'Brasil'")
    private String pais;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
