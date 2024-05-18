package br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CardDuplicateException extends RuntimeException {
    public CardDuplicateException(String msg) {
        super(msg);
    }
}
