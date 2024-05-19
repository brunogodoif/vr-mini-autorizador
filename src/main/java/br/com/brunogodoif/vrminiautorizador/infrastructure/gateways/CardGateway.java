package br.com.brunogodoif.vrminiautorizador.infrastructure.gateways;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.UpdateBalance;
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface;
import br.com.brunogodoif.vrminiautorizador.infrastructure.gateways.exceptions.CardNotFoundException;
import br.com.brunogodoif.vrminiautorizador.infrastructure.mapper.CardMapper;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.Card;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardGateway implements CardGatewayInterface {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card createCard(br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card card) {
        Card cardEntity = cardMapper.toEntity(card);
        Card cardCreated = cardRepository.save(cardEntity);
        return cardMapper.toDomainObj(cardCreated);
    }

    @Override
    public br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card getCard(String cardNumber) {
        Optional<Card> byCardNumber = cardRepository.findByCardNumber(cardNumber);
        return byCardNumber.map(cardMapper::toDomainObj).orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    @Override
    public boolean cardExists(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber).isPresent();
    }

    @Override
    @Transactional
    public void updateBalance(UpdateBalance updateBalance) {
        Optional<Card> cardEntityOpt = cardRepository.findByCardNumber(updateBalance.getCardNumber());
        if (cardEntityOpt.isEmpty())
            throw new CardNotFoundException("Card not found");

        Card cardEntity = cardEntityOpt.get();
        cardEntity.setBalance(updateBalance.getAmount());
        cardRepository.save(cardEntity);
    }
}