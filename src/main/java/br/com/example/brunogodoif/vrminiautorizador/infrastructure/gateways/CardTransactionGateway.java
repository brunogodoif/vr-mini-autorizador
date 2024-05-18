package br.com.example.brunogodoif.vrminiautorizador.infrastructure.gateways;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction;
import br.com.example.brunogodoif.vrminiautorizador.application.gateways.CardTransactionGatewayInterface;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTransactionGateway implements CardTransactionGatewayInterface {

    @Autowired
    private final CardTransactionRepository cardTransactionRepository;

    @Override
    public List<CardTransaction> getAllTransactions() {
        return List.of();
    }

    @Override
    public CardTransaction makeTransaction(CardTransaction cardTransaction) {
        return null;
    }
}
