package br.com.brunogodoif.vrminiautorizador.infrastructure.mapper;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardNumber;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardPassword;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransactionCreate;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardTransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardTransactionMapper {

    public br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction toDomainObject(CardTransactionEntity cardTransactionEntity) {
        br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card card = new br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card(
                cardTransactionEntity.getCard().getId(),
                new CardNumber(cardTransactionEntity.getCard().getCardNumber()),
                new CardPassword(cardTransactionEntity.getCard().getPassword()),
                cardTransactionEntity.getCard().getBalance()
        );

        return new br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction(
                cardTransactionEntity.getId(),
                card,
                cardTransactionEntity.getValue(),
                cardTransactionEntity.getPreviousBalance(),
                cardTransactionEntity.getNewBalance(),
                cardTransactionEntity.getStatus(),
                cardTransactionEntity.getCreatedAt(),
                cardTransactionEntity.getUpdatedAt()
        );
    }

    public CardTransactionEntity toEntity(br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction cardTransaction) {

        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(cardTransaction.getCard().getId());
        cardEntity.setCardNumber(cardTransaction.getCard().getCardNumber().getNumber());
        cardEntity.setPassword(cardTransaction.getCard().getCardPassword().getPassword());
        cardEntity.setBalance(cardTransaction.getCard().getBalance());

        CardTransactionEntity cardTransactionEntity = new CardTransactionEntity();
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

    public CardTransactionEntity toEntity(CardTransactionCreate cardTransaction) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(cardTransaction.getCard().getId());
        cardEntity.setCardNumber(cardTransaction.getCard().getCardNumber().getNumber());
        cardEntity.setPassword(cardTransaction.getCard().getCardPassword().getPassword());
        cardEntity.setBalance(cardTransaction.getCard().getBalance());

        CardTransactionEntity cardTransactionEntity = new CardTransactionEntity();
        cardTransactionEntity.setCard(cardEntity);
        cardTransactionEntity.setValue(cardTransaction.getValue());
        cardTransactionEntity.setPreviousBalance(cardTransaction.getPreviousBalance());
        cardTransactionEntity.setNewBalance(cardTransaction.getNewBalance());
        cardTransactionEntity.setStatus(cardTransaction.getStatus().toString());

        return cardTransactionEntity;
    }
}
