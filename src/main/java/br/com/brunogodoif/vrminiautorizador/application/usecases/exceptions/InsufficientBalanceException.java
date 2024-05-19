package br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
