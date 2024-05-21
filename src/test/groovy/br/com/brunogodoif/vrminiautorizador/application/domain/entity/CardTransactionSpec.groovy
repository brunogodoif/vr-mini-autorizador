package br.com.brunogodoif.vrminiautorizador.application.domain.entity

import spock.lang.Specification

import java.time.LocalDateTime

class CardTransactionSpec extends Specification {
    def "Should create CardTransaction with valid card and values"() {
        given:
        UUID id = UUID.randomUUID()
        CardNumber cardNumber = new CardNumber("1234567890123456")
        CardPassword cardPassword = new CardPassword("1234")
        BigDecimal balance = BigDecimal.valueOf(500.00)
        Card card = new Card(id, cardNumber, cardPassword, balance)

        BigDecimal value = BigDecimal.valueOf(100.00)
        BigDecimal previousBalance = BigDecimal.valueOf(500.00)
        BigDecimal newBalance = BigDecimal.valueOf(600.00)
        LocalDateTime createdAt = LocalDateTime.now()
        LocalDateTime updatedAt = LocalDateTime.now()

        when:
        def transaction = new CardTransaction(id, card, value, previousBalance, newBalance, "SUCCESS", createdAt, updatedAt)

        then:
        transaction.getId() == id
        transaction.getCard() == card
        transaction.getValue().compareTo(value) == 0
        transaction.getPreviousBalance().compareTo(previousBalance) == 0
        transaction.getNewBalance().compareTo(newBalance) == 0
        transaction.getStatus() == "SUCCESS"
        transaction.getCreatedAt() == createdAt
        transaction.getUpdatedAt() == updatedAt
    }

    def "Should create CardTransaction with valid card and values using second constructor"() {
        given:
        UUID id = UUID.randomUUID()
        CardNumber cardNumber = new CardNumber("1234567890123456")
        CardPassword cardPassword = new CardPassword("1234")
        BigDecimal balance = BigDecimal.valueOf(500.00)
        Card card = new Card(id, cardNumber, cardPassword, balance)

        BigDecimal value = BigDecimal.valueOf(100.00)
        BigDecimal previousBalance = BigDecimal.valueOf(500.00)
        BigDecimal newBalance = BigDecimal.valueOf(600.00)

        when:
        def transaction = new CardTransaction(card, value, previousBalance, newBalance, "SUCCESS")

        then:
        transaction.getCard() == card
        transaction.getValue().compareTo(value) == 0
        transaction.getPreviousBalance().compareTo(previousBalance) == 0
        transaction.getNewBalance().compareTo(newBalance) == 0
        transaction.getStatus() == "SUCCESS"
    }
}
