package com.pousada.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class HospedeDTO {

    private Integer id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private Date dataNascimento;

    private String email;

    private String telefone;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String pais;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

}
