package br.com.example.brunogodoif.vrminiautorizador.application.domain.usecases;

import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardTransactionRequest;

public interface MakeCardTransactionInterface {
    void execute(CardTransactionRequest cardTransactionRequest);
}
