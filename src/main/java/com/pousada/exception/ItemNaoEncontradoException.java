package com.pousada.exception;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
