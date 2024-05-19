package br.com.example.brunogodoif.vrminiautorizador.infrastructure.mapper;

import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.Card;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardTransactionMapper {

    public br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction toDomainObject(CardTransaction cardTransaction) {
        br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card card = new br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card(
                cardTransaction.getCard().getId(),
                cardTransaction.getCard().getCardNumber(),
                cardTransaction.getCard().getPassword(),
                cardTransaction.getCard().getBalance()
        );

        return new br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction(
                cardTransaction.getId(),
                card,
                cardTransaction.getValue(),
                cardTransaction.getPreviousBalance(),
                cardTransaction.getNewBalance(),
                cardTransaction.getStatus(),
                cardTransaction.getCreatedAt(),
                cardTransaction.getUpdatedAt()
        );
    }

    public CardTransaction toEntity(br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction cardTransaction) {

        Card cardEntity = new Card();
        cardEntity.setId(cardTransaction.getCard().getId());
        cardEntity.setCardNumber(cardTransaction.getCard().getCardNumber());
        cardEntity.setPassword(cardTransaction.getCard().getPassword());
        cardEntity.setBalance(cardTransaction.getCard().getBalance());

        CardTransaction cardTransactionEntity = new CardTransaction();
        cardTransactionEntity.setId(cardTransaction.getId());
        cardTransactionEntity.setCard(cardEntity);
        cardTransactionEntity.setValue(cardTransaction.getValue());
        cardTransactionEntity.setPreviousBalance(cardTransaction.getPreviousBalance());
        cardTransactionEntity.setNewBalance(cardTransaction.getNewBalance());
        cardTransactionEntity.setStatus(cardTransaction.getStatus());
        cardTransactionEntity.setCreatedAt(cardTransaction.getCreatedAt());
        cardTransactionEntity.setUpdatedAt(cardTransaction.getUpdatedAt());

        return cardTransactionEntity;
    }

}
