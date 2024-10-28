package com.pousada.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HospedeDTO {

    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private String telefone;

    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;

    private String pais;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

}
