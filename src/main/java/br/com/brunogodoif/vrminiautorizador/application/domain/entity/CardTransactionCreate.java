package br.com.brunogodoif.vrminiautorizador.application.domain.entity;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidTransactionException;

import java.math.BigDecimal;

public class CardTransactionCreate {

    private Card card;
    private BigDecimal value;
    private BigDecimal previousBalance;
    private BigDecimal newBalance;
    private TransactionStatus status;

    public CardTransactionCreate(Card card, BigDecimal value, BigDecimal previousBalance, BigDecimal newBalance, TransactionStatus status) {
        this.card = card;
        this.value = value;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.status = status;
        validate();
    }

    public Card getCard() {
        return card;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    private void validate() {
        if (value.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidTransactionException("O valor da transação deve ser maior que 0.00");


        if (previousBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidTransactionException("O saldo anterior não pode ser menor que 0.00");


        if (newBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidTransactionException("O novo saldo não pode ser menor que 0.00");

    }
}