package br.com.brunogodoif.vrminiautorizador.application.gateways;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransactionCreate;

public interface CardTransactionGatewayInterface {
    CardTransaction persistTransaction(CardTransactionCreate cardTransaction);
}
