package br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions;

public class InvalidCardException extends RuntimeException {
    public InvalidCardException(String msg) {
        super(msg);
    }
}