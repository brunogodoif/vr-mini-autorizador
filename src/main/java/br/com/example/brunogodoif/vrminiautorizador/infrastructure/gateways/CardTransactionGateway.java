package br.com.example.brunogodoif.vrminiautorizador.infrastructure.gateways;

import br.com.example.brunogodoif.vrminiautorizador.application.gateways.CardTransactionGatewayInterface;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.mapper.CardTransactionMapper;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.Card;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardTransaction;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardTransactionRepository;
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
    public List<br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction> getAllTransactions() {
        return List.of();
    }

    @Override
    @Transactional
    public br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction persistTransaction(br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction cardTransaction) {
        Card card = cardRepository.findById(cardTransaction.getCard().getId())
                .orElseThrow(() -> new RuntimeException("Card not found"));


        CardTransaction entity = cardTransactionMapper.toEntity(cardTransaction);
        entity.setCard(card);
        CardTransaction cardTransactionCreated = cardTransactionRepository.save(entity);

        return cardTransactionMapper.toDomainObject(cardTransactionCreated);
    }
}
