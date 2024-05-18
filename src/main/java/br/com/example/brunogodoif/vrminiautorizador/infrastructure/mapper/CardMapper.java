package br.com.example.brunogodoif.vrminiautorizador.infrastructure.mapper;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public Card toDomainObj(CardEntity cardEntity) {
        return new Card(
                cardEntity.getCardNumber(),
                cardEntity.getPassword(),
                cardEntity.getBalance()
        );
    }

    public CardEntity toEntity(Card card) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNumber(card.getCardNumber());
        cardEntity.setPassword(card.getPassword());
        cardEntity.setBalance(card.getBalance());
        return cardEntity;
    }
}