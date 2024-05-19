package br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.usecases.MakeCardTransactionInterface;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardTransactionRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class CardTransactionController {

    private final MakeCardTransactionInterface makeCardTransaction;

    @PostMapping
    public ResponseEntity<String> makeTransaction(@Valid @RequestBody CardTransactionRequest cardTransactionRequest) {
        makeCardTransaction.execute(cardTransactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
}