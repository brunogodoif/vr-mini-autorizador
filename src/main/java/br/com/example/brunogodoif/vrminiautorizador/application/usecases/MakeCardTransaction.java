package br.com.example.brunogodoif.vrminiautorizador.application.usecases;

import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.Card;
import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction;
import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.TransactionStatus;
import br.com.example.brunogodoif.vrminiautorizador.application.domain.entity.UpdateBalance;
import br.com.example.brunogodoif.vrminiautorizador.application.domain.usecases.MakeCardTransactionInterface;
import br.com.example.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface;
import br.com.example.brunogodoif.vrminiautorizador.application.gateways.CardTransactionGatewayInterface;
import br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardNotFoundException;
import br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions.InsufficientBalanceException;
import br.com.example.brunogodoif.vrminiautorizador.application.usecases.exceptions.InvalidPasswordException;
import br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardTransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MakeCardTransaction implements MakeCardTransactionInterface {

    private final CardGatewayInterface cardGateway;
    private final CardTransactionGatewayInterface cardTransactionGateway;


    public void execute(CardTransactionRequest cardTransactionRequest) {
        String cardNumber = cardTransactionRequest.getNumeroCartao();

        if (!cardGateway.cardExists(cardNumber))
            throw new CardNotFoundException(TransactionStatus.CARTAO_INEXISTENTE.toString());

        Card card = cardGateway.getCard(cardNumber);
        BigDecimal transactionValue = BigDecimal.valueOf(cardTransactionRequest.getValor());

        if (!card.getPassword().equals(cardTransactionRequest.getSenhaCartao()))
            throw new InvalidPasswordException(TransactionStatus.SENHA_INVALIDA.toString());

        if (card.getBalance().compareTo(transactionValue) < 0) {
            CardTransaction cardTransaction = buildTransactionToPersist(card, transactionValue, TransactionStatus.SALDO_INSUFICIENTE);
            cardTransactionGateway.persistTransaction(cardTransaction);
            throw new InsufficientBalanceException(TransactionStatus.SALDO_INSUFICIENTE.toString());
        }

        CardTransaction cardTransaction = buildTransactionToPersist(card, transactionValue, TransactionStatus.OK);
        cardTransactionGateway.persistTransaction(cardTransaction);

        UpdateBalance updateBalance = new UpdateBalance(cardNumber, cardTransaction.getNewBalance());
        cardGateway.updateBalance(updateBalance);

    }

    private CardTransaction buildTransactionToPersist(Card card, BigDecimal transactionValue, TransactionStatus status) {
        BigDecimal previousBalance = card.getBalance();
        BigDecimal newBalance = (status == TransactionStatus.OK) ? previousBalance.subtract(transactionValue) : previousBalance;
        return new CardTransaction(card, transactionValue, previousBalance, newBalance, status.toString());
    }

}
