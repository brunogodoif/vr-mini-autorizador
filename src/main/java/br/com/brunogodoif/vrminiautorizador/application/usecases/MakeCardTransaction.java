package br.com.brunogodoif.vrminiautorizador.application.usecases;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.*;
import br.com.brunogodoif.vrminiautorizador.application.domain.usecases.MakeCardTransactionInterface;
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface;
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardTransactionGatewayInterface;
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardNotFoundException;
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.InsufficientBalanceException;
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.InvalidPasswordException;
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardTransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MakeCardTransaction implements MakeCardTransactionInterface {

    private final CardGatewayInterface cardGateway;
    private final CardTransactionGatewayInterface cardTransactionGateway;


    public void execute(CardTransactionRequest cardTransactionRequest) {

        Card cardToTransaction = new Card(
                new CardNumber(cardTransactionRequest.numeroCartao()),
                new CardPassword(cardTransactionRequest.senhaCartao())
        );

        if (!cardGateway.cardExists(cardToTransaction.getCardNumber().getNumber()))
            throw new CardNotFoundException(TransactionStatus.CARTAO_INEXISTENTE.toString());

        Card card = cardGateway.getCard(cardToTransaction.getCardNumber().getNumber());
        BigDecimal transactionValue = BigDecimal.valueOf(cardTransactionRequest.valor());

        if (!card.getCardPassword().getPassword().equals(cardTransactionRequest.senhaCartao()))
            throw new InvalidPasswordException(TransactionStatus.SENHA_INVALIDA.toString());

        if (card.getBalance().compareTo(transactionValue) < 0) {
            CardTransactionCreate cardTransaction = buildTransactionToPersist(card, transactionValue, TransactionStatus.SALDO_INSUFICIENTE);
            cardTransactionGateway.persistTransaction(cardTransaction);
            throw new InsufficientBalanceException(TransactionStatus.SALDO_INSUFICIENTE.toString());
        }

        CardTransactionCreate cardTransaction = buildTransactionToPersist(card, transactionValue, TransactionStatus.OK);
        cardTransactionGateway.persistTransaction(cardTransaction);

        CardUpdateBalance cardUpdateBalance = new CardUpdateBalance(new CardNumber(cardTransactionRequest.numeroCartao()), cardTransaction.getNewBalance());
        cardGateway.updateBalance(cardUpdateBalance);

    }

    private CardTransactionCreate buildTransactionToPersist(Card card, BigDecimal transactionValue, TransactionStatus status) {
        BigDecimal previousBalance = card.getBalance();
        BigDecimal newBalance = (status == TransactionStatus.OK) ? previousBalance.subtract(transactionValue) : previousBalance;
        return new CardTransactionCreate(card, transactionValue, previousBalance, newBalance, status);
    }

}
