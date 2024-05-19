package br.com.brunogodoif.vrminiautorizador.application.domain.usecases;

import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest;
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.response.CardCreatedResponse;

public interface CreateCardInterface {
    CardCreatedResponse execute(CardCreateRequest cardCreateRequest);
}
