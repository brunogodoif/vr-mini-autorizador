package br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.usecases.CreateCardInterface;
import br.com.example.brunogodoif.vrminiautorizador.application.domain.usecases.GetBalanceInterface;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.response.CardCreatedResponse;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.exceptions.InvalidCardNumberException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CardController {

    private final CreateCardInterface createCard;

    private final GetBalanceInterface getBalance;

    @PostMapping
    public ResponseEntity<CardCreatedResponse> createCard(@Valid @RequestBody CardCreateRequest cardCreateRequest) {
        var card = createCard.execute(cardCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @GetMapping("/{numeroCartao}")
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
