package com.pousada.exception;

public class UsuarioJaExistenteException extends RuntimeException {

    public UsuarioJaExistenteException(String mensagem) {
        super(mensagem);
    }

}