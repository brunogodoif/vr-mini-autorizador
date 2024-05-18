package br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers;

import br.com.example.brunogodoif.vrminiautorizador.application.usecases.CreateCard;
import br.com.example.brunogodoif.vrminiautorizador.application.usecases.GetBalance;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.response.CardCreatedResponse;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.exceptions.InvalidCardNumberException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CardController {

    @Autowired
    private final CreateCard createCard;

    @Autowired
    private final GetBalance getBalance;


    @PostMapping
    public ResponseEntity<CardCreatedResponse> createCard(@Valid @RequestBody CardCreateRequest cardCreateRequest) {
        var card = createCard.execute(cardCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<Long> getBalance(@PathVariable String numeroCartao) {
        validateCardNumber(numeroCartao);

        Long balance = getBalance.execute(numeroCartao);
        return ResponseEntity.status(HttpStatus.CREATED).body(balance);
    }

    private void validateCardNumber(String numeroCartao) {
        String regex = "\\d{16}";
        if (!numeroCartao.matches(regex)) {
            throw new InvalidCardNumberException("o número do cartão deve ter exatamente 16 dígitos");
        }
    }

}
