package br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions;

public class InvalidBalanceUpdateException extends RuntimeException {
    public InvalidBalanceUpdateException(String message) {
        super(message);
    }
}