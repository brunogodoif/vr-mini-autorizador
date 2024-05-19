package br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCardNumberException extends RuntimeException {
    public InvalidCardNumberException(String msg) {
        super(msg);
    }
}
