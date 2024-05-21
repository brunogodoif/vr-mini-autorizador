package br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String msg) {
        super(msg);
    }
}