package br.com.brunogodoif.vrminiautorizador.infrastructure.gateways.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String msg) {
        super(msg);
    }
}
