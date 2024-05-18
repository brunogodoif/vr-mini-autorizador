package br.com.example.brunogodoif.vrminiautorizador.application.usecases;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.example.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface;
import br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardDuplicateException;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.response.CardCreatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCard {

    private final CardGatewayInterface cardGateway;

    @Value("${default.card.balance}")
    private Long defaultCardBalance;

    public CardCreatedResponse execute(CardCreateRequest cardCreateRequest) {

        if (cardGateway.cardExists(cardCreateRequest.getNumeroCartao()))
            throw new CardDuplicateException("Cartão já existe na base de dados.");


        Card cardToCreate = new Card(cardCreateRequest.getNumeroCartao(), cardCreateRequest.getSenha(), defaultCardBalance);
        Card cardCreated = cardGateway.createCard(cardToCreate);
        return new CardCreatedResponse(cardCreated.getCardNumber(), cardCreated.getPassword());
    }
}