package br.com.example.brunogodoif.vrminiautorizador.application.gateways;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction;

import java.util.List;

public interface CardTransactionGatewayInterface {
    List<CardTransaction> getAllTransactions();

    CardTransaction persistTransaction(CardTransaction cardTransaction);
}
