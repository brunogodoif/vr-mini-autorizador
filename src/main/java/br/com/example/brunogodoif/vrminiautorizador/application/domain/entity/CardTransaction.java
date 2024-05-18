package br.com.example.brunogodoif.vrminiautorizador.application.domain.entity;

import java.time.LocalDateTime;

public class CardTransaction {

    private String cardNumber;
    private double value;
    private LocalDateTime createdAt;

    public CardTransaction(String cardNumber, double value) {
        this.cardNumber = cardNumber;
        this.value = value;
        this.createdAt = LocalDateTime.now();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
