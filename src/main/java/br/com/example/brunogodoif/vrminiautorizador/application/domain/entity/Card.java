package br.com.example.brunogodoif.vrminiautorizador.application.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Card {

    private UUID id;
    private String cardNumber;
    private String password;
    private BigDecimal balance;

    public Card(UUID id, String cardNumber, String password, BigDecimal balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.password = password;
        this.balance = balance;
    }

    public Card(String cardNumber, String password, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getBalance() {
        return balance;
    }


}
