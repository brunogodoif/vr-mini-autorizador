package br.com.example.brunogodoif.vrminiautorizador.application.usecases;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.example.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface;
import br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBalance {

    private final CardGatewayInterface cardGateway;

    public Long execute(String cardNumber) {

        if (!cardGateway.cardExists(cardNumber))
            throw new CardNotFoundException("Cartão não localizado.");

        Card cardFind = cardGateway.getCard(cardNumber);
        return cardFind.getBalance();
    }
}
