package br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers;

import br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardDuplicateException;
import br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardNotFoundException;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.exceptions.InvalidCardNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCardNumberException.class)
    public ResponseEntity<Object> handleInvalidCardNumberException(InvalidCardNumberException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardDuplicateException.class)
    public ResponseEntity<Object> handleCardDuplicateException(CardDuplicateException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Object> handleCardNotFoundException(CardNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
