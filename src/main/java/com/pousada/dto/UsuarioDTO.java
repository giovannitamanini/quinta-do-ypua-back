package com.pousada.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String usuario;

    private String senha;

    private boolean ativo;
}
