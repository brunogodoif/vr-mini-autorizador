package br.com.brunogodoif.vrminiautorizador.infrastructure.controllers;

import br.com.brunogodoif.vrminiautorizador.application.domain.usecases.CreateCardInterface;
import br.com.brunogodoif.vrminiautorizador.application.domain.usecases.GetBalanceInterface;
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest;
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.response.CardCreatedResponse;
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.exceptions.InvalidCardNumberException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
@Tag(name = "Cartões", description = "Endpoints para operações relacionadas a cartões")
public class CardController {

    private final CreateCardInterface createCard;

    private final GetBalanceInterface getBalance;

    @PostMapping
    @Operation(summary = "Criar um novo cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartão criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<CardCreatedResponse> createCard(@Valid @RequestBody CardCreateRequest cardCreateRequest) {
        var card = createCard.execute(cardCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @GetMapping("/{numeroCartao}")
    @Operation(summary = "Obter saldo do cartão pelo número do cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo obtido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Número do cartão inválido"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String numeroCartao) {
        validateCardNumber(numeroCartao);

        BigDecimal balance = getBalance.execute(numeroCartao);
        return ResponseEntity.status(HttpStatus.CREATED).body(balance);
    }

    private void validateCardNumber(String numeroCartao) {
        String regex = "\\d{16}";
        if (!numeroCartao.matches(regex)) {
            throw new InvalidCardNumberException("o número do cartão deve ter exatamente 16 dígitos");
        }
    }

}
