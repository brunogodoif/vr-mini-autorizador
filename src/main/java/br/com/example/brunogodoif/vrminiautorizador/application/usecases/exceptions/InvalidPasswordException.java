package br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String msg) {
        super(msg);
    }
}
