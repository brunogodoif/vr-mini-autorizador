package br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions;

public class InvalidCardPasswordException extends RuntimeException {
    public InvalidCardPasswordException(String msg) {
        super(msg);
    }
}