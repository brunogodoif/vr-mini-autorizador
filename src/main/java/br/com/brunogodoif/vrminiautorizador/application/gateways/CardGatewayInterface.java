package br.com.brunogodoif.vrminiautorizador.application.gateways;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.UpdateBalance;

public interface CardGatewayInterface {
    Card createCard(Card card);

    void updateBalance(UpdateBalance updateBalance);

    Card getCard(String cardNumber);

    boolean cardExists(String cardNumber);
}
