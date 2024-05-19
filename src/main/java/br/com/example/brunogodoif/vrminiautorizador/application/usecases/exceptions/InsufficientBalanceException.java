package br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
