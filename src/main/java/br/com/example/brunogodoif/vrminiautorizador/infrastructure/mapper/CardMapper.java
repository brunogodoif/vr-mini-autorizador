package br.com.example.brunogodoif.vrminiautorizador.infrastructure.mapper;

import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card toDomainObj(Card cardEntity) {
        return new br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card(
                cardEntity.getId(),
                cardEntity.getCardNumber(),
                cardEntity.getPassword(),
                cardEntity.getBalance()
        );
    }

    public Card toEntity(br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card card) {
        Card cardEntity = new Card();
        cardEntity.setId(card.getId());
        cardEntity.setCardNumber(card.getCardNumber());
        cardEntity.setPassword(card.getPassword());
        cardEntity.setBalance(card.getBalance());
        return cardEntity;
    }
}