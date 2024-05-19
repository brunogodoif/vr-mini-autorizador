package br.com.brunogodoif.vrminiautorizador.infrastructure.controllers;

import br.com.brunogodoif.vrminiautorizador.application.domain.usecases.MakeCardTransactionInterface;
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardTransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Transações", description = "Endpoints para operações relacionadas a transações")
public class CardTransactionController {

    private final MakeCardTransactionInterface makeCardTransaction;

    @PostMapping
    @Operation(summary = "Realizar uma transação com um cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<String> makeTransaction(@Valid @RequestBody CardTransactionRequest cardTransactionRequest) {
        makeCardTransaction.execute(cardTransactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
}