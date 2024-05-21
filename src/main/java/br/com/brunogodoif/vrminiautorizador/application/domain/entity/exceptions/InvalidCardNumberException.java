package br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions;

public class InvalidCardNumberException extends RuntimeException {
    public InvalidCardNumberException(String message) {
        super(message);
    }
}