package br.com.example.brunogodoif.vrminiautorizador.application.domain.usecases;

import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.response.CardCreatedResponse;

public interface CreateCardInterface {
    CardCreatedResponse execute(CardCreateRequest cardCreateRequest);
}
