package br.com.brunogodoif.vrminiautorizador.application.usecases;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardCreate;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardNumber;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardPassword;
import br.com.brunogodoif.vrminiautorizador.application.domain.usecases.CreateCardInterface;
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface;
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardDuplicateException;
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest;
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.response.CardCreatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateCard implements CreateCardInterface {

    private final CardGatewayInterface cardGateway;

    @Value("${default.card.balance}")
    private BigDecimal defaultCardBalance;

    @Override
    public CardCreatedResponse execute(CardCreateRequest cardCreateRequest) {

        CardNumber cardNumber = new CardNumber(cardCreateRequest.numeroCartao());
        CardPassword cardPassword = new CardPassword(cardCreateRequest.senha());

        if (cardGateway.cardExists(cardNumber.getNumber()))
            throw new CardDuplicateException("Cartão já existe na base de dados.");

        CardCreate cardToCreate = new CardCreate(cardNumber, cardPassword, defaultCardBalance);
        Card cardCreated = cardGateway.createCard(cardToCreate);
        return new CardCreatedResponse(cardCreated.getCardNumber().getNumber(), cardCreated.getCardPassword().getPassword());
    }
}