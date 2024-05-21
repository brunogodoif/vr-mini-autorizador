package br.com.brunogodoif.vrminiautorizador.application.gateways;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardCreate;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardUpdateBalance;

public interface CardGatewayInterface {
    Card createCard(CardCreate card);

    boolean updateBalance(CardUpdateBalance cardUpdateBalance);

    Card getCard(String cardNumber);

    boolean cardExists(String cardNumber);
}
