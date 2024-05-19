package br.com.brunogodoif.vrminiautorizador.application.domain.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CardTransaction {

    private UUID id;
    private Card card;
    private BigDecimal value;
    private BigDecimal previousBalance;
    private BigDecimal newBalance;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public CardTransaction(UUID id, Card card, BigDecimal value, BigDecimal previousBalance, BigDecimal newBalance, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.card = card;
        this.value = value;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CardTransaction(Card card, BigDecimal value, BigDecimal previousBalance, BigDecimal newBalance, String status) {
        this.card = card;
        this.value = value;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.status = status;
    }

    public UUID getId() {
        return id;
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
