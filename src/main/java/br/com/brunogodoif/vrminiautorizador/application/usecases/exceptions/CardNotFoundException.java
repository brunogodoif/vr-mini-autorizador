package br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String msg) {
        super(msg);
    }
}
