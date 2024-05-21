package br.com.brunogodoif.vrminiautorizador.application.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Card {

    private UUID id;
    private CardNumber cardNumber;
    private CardPassword cardPassword;
    private BigDecimal balance;

    public Card(UUID id, CardNumber cardNumber, CardPassword cardPassword, BigDecimal balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.balance = balance;
    }

    public Card(CardNumber cardNumber, CardPassword cardPassword) {
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
    }

    public UUID getId() {
        return id;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardPassword getCardPassword() {
        return cardPassword;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
