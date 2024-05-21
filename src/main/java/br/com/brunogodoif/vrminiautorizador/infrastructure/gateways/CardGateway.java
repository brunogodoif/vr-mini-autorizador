package br.com.brunogodoif.vrminiautorizador.infrastructure.gateways;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardCreate;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardUpdateBalance;
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface;
import br.com.brunogodoif.vrminiautorizador.infrastructure.gateways.exceptions.CardNotFoundException;
import br.com.brunogodoif.vrminiautorizador.infrastructure.mapper.CardMapper;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardGateway implements CardGatewayInterface {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card createCard(CardCreate card) {
        CardEntity cardEntity = cardMapper.toEntity(card);
        CardEntity cardEntityCreated = cardRepository.save(cardEntity);
        return cardMapper.toDomainObj(cardEntityCreated);
    }

    @Override
    public br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card getCard(String cardNumber) {
        Optional<CardEntity> byCardNumber = cardRepository.findByCardNumber(cardNumber);
        return byCardNumber.map(cardMapper::toDomainObj).orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    @Override
    public boolean cardExists(String cardNumber) {
        return cardRepository.existsByCardNumber(cardNumber);
    }

    @Override
    public boolean updateBalance(CardUpdateBalance cardUpdateBalance) {
        Optional<CardEntity> cardEntityOpt = cardRepository.findByCardNumber(cardUpdateBalance.getCard().getNumber());
        if (cardEntityOpt.isEmpty())
            throw new CardNotFoundException("Card not found");

        CardEntity cardEntity = cardEntityOpt.get();
        cardEntity.setBalance(cardUpdateBalance.getAmount());
        cardRepository.save(cardEntity);
        return true;
    }
}