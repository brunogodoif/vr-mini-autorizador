package br.com.brunogodoif.vrminiautorizador.infrastructure.mapper;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardCreate;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardNumber;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardPassword;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card toDomainObj(CardEntity cardEntity) {
        return new Card(
                cardEntity.getId(),
                new CardNumber(cardEntity.getCardNumber()),
                new CardPassword(cardEntity.getPassword()),
                cardEntity.getBalance()
        );
    }

    public CardEntity toEntity(Card card) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(card.getId());
        cardEntity.setCardNumber(card.getCardNumber().getNumber());
        cardEntity.setPassword(card.getCardPassword().getPassword());
        cardEntity.setBalance(card.getBalance());
        return cardEntity;
    }

    public CardEntity toEntity(CardCreate card) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(card.getCard().getNumber());
        cardEntity.setPassword(card.getCardPassword().getPassword());
        cardEntity.setBalance(card.getBalance());
        return cardEntity;
    }
}