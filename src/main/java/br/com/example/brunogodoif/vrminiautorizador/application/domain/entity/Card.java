package br.com.example.brunogodoif.vrminiautorizador.application.domain.entity;

public class Card {

    private String cardNumber;
    private String password;
    private Long balance;

    public Card(String cardNumber, String password, Long balance) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public Long getBalance() {
        return balance;
    }
}
