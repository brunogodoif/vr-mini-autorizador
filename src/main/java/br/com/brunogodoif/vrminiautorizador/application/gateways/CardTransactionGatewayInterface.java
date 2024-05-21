package br.com.brunogodoif.vrminiautorizador.application.gateways;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransactionCreate;

import java.util.List;

public interface CardTransactionGatewayInterface {
    List<CardTransaction> getAllTransactions();

    CardTransaction persistTransaction(CardTransactionCreate cardTransaction);
}
