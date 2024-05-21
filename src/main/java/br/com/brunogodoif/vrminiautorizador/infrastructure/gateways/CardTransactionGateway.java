package br.com.brunogodoif.vrminiautorizador.infrastructure.gateways;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction;
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransactionCreate;
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardTransactionGatewayInterface;
import br.com.brunogodoif.vrminiautorizador.infrastructure.gateways.exceptions.CardNotFoundException;
import br.com.brunogodoif.vrminiautorizador.infrastructure.mapper.CardTransactionMapper;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardTransactionEntity;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository;
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTransactionGateway implements CardTransactionGatewayInterface {

    private final CardRepository cardRepository;
    private final CardTransactionRepository cardTransactionRepository;
    private final CardTransactionMapper cardTransactionMapper;

    @Override
    public List<br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction> getAllTransactions() {
        return List.of();
    }

    @Override
    @Transactional
    public CardTransaction persistTransaction(CardTransactionCreate cardTransaction) {

        CardEntity cardEntity = cardRepository.findByCardNumber(cardTransaction.getCard().getCardNumber().getNumber())
                .orElseThrow(() -> new CardNotFoundException("Card not found"));

        CardTransactionEntity entity = cardTransactionMapper.toEntity(cardTransaction);
        entity.setCard(cardEntity);
        CardTransactionEntity cardTransactionEntityCreated = cardTransactionRepository.save(entity);

        return cardTransactionMapper.toDomainObject(cardTransactionEntityCreated);
    }
}
