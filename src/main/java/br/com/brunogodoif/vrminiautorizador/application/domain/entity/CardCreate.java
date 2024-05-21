package br.com.brunogodoif.vrminiautorizador.application.domain.entity;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CardCreate {

    private static final Pattern DIGITS_ONLY = Pattern.compile("\\d+");

    private final CardNumber card;
    private final CardPassword cardPassword;
    private final BigDecimal balance;

    public CardCreate(CardNumber card, CardPassword cardPassword, BigDecimal balance) {
        this.card = card;
        this.cardPassword = cardPassword;
        this.balance = balance;
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

}