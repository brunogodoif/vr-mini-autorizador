package br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions;

public class InvalidCardCreateException extends RuntimeException {
    public InvalidCardCreateException(String message) {
        super(message);
    }
}