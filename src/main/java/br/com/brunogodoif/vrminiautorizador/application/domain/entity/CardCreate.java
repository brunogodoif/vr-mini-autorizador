package br.com.brunogodoif.vrminiautorizador.application.domain.entity;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardCreateException;

import java.math.BigDecimal;

public class CardCreate {

    private final CardNumber card;
    private final CardPassword cardPassword;
    private final BigDecimal balance;

    public CardCreate(CardNumber card, CardPassword cardPassword, BigDecimal balance) {
        this.card = card;
        this.cardPassword = cardPassword;
        this.balance = balance;
        validate();
    }

    public CardNumber getCard() {
        return card;
    }

    public CardPassword getCardPassword() {
        return cardPassword;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void validate() {

        if (balance == null || balance.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidCardCreateException("O valor deve ser maior que 0.00");

    }
}