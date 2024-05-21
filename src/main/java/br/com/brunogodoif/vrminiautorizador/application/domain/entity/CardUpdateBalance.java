package br.com.brunogodoif.vrminiautorizador.application.domain.entity;

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidBalanceUpdateException;

import java.math.BigDecimal;

public class CardUpdateBalance {

    private final CardNumber card;
    private final BigDecimal amount;

    public CardUpdateBalance(CardNumber card, BigDecimal amount) {
        this.card = card;
        this.amount = amount;
        validate();
    }

    public CardNumber getCard() {
        return card;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void validate() {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidBalanceUpdateException("O valor deve ser maior que 0.00");

    }

}
