package br.com.brunogodoif.vrminiautorizador.application.domain.usecases;

import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardTransactionRequest;

public interface MakeCardTransactionInterface {
    boolean execute(CardTransactionRequest cardTransactionRequest);
}
