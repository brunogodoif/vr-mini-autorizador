package br.com.example.brunogodoif.vrminiautorizador.infrastructure.gateways;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.example.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.gateways.exceptions.CardNotFoundException;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.mapper.CardMapper;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardGateway implements CardGatewayInterface {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public Card createCard(Card card) {
        CardEntity cardEntity = cardMapper.toEntity(card);
        CardEntity cardCreated = cardRepository.save(cardEntity);
        return cardMapper.toDomainObj(cardCreated);
    }

    @Override
    public Card getCard(String cardNumber) {
        Optional<CardEntity> byCardNumber = cardRepository.findByCardNumber(cardNumber);
        return byCardNumber.map(cardMapper::toDomainObj).orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    @Override
    public boolean cardExists(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber).isPresent();
    }

}