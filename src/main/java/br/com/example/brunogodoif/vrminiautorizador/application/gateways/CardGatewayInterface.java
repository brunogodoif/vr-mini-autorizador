package br.com.example.brunogodoif.vrminiautorizador.application.gateways;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card;

public interface CardGatewayInterface {
    Card createCard(Card card);

    Card getCard(String cardNumber);

    boolean cardExists(String cardNumber);
}
