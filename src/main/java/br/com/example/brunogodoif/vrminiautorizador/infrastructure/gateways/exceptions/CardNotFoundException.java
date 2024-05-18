package br.com.example.brunogodoif.vrminiautorizador.infrastructure.gateways.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String msg) {
        super(msg);
    }
}
