package br.com.brunogodoif.vrminiautorizador.application.domain.entity

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidTransactionException
import spock.lang.Specification

class CardTransactionCreateSpec extends Specification {

    def "Should create CardTransactionCreate with valid card and values"() {
        given:
        UUID id = UUID.randomUUID()
        CardNumber cardNumber = new CardNumber("1234567890123456")
        CardPassword cardPassword = new CardPassword("1234")
        BigDecimal balance = BigDecimal.valueOf(500.00)
        Card card = new Card(id, cardNumber, cardPassword, balance)

        BigDecimal value = BigDecimal.valueOf(100.0)
        BigDecimal previousBalance = BigDecimal.valueOf(500.0)
        BigDecimal newBalance = BigDecimal.valueOf(600.0)

        when:
        def transactionCreate = new CardTransactionCreate(card, value, previousBalance, newBalance, TransactionStatus.OK)

        then:
        transactionCreate.getCard() == card
        transactionCreate.getValue().compareTo(value) == 0
        transactionCreate.getPreviousBalance().compareTo(previousBalance) == 0
        transactionCreate.getNewBalance().compareTo(newBalance) == 0
        transactionCreate.getStatus() == TransactionStatus.OK
    }

    def "Should not create CardTransactionCreate with value transaction less than zero"() {
        given:
        UUID id = UUID.randomUUID()
        CardNumber cardNumber = new CardNumber("1234567890123456")
        CardPassword cardPassword = new CardPassword("1234")
        BigDecimal balance = BigDecimal.valueOf(500.00)
        Card card = new Card(id, cardNumber, cardPassword, balance)

        when:
        new CardTransactionCreate(card, BigDecimal.valueOf(-100.0), BigDecimal.valueOf(500.0), BigDecimal.valueOf(600.0), TransactionStatus.OK)

        then:
        thrown(InvalidTransactionException)
    }

    def "Should not create CardTransactionCreate with previous balance less than zero"() {
        given:
        UUID id = UUID.randomUUID()
        CardNumber cardNumber = new CardNumber("1234567890123456")
        CardPassword cardPassword = new CardPassword("1234")
        BigDecimal balance = BigDecimal.valueOf(500.00)
        Card card = new Card(id, cardNumber, cardPassword, balance)

        when:
        new CardTransactionCreate(card, BigDecimal.valueOf(100.0), BigDecimal.valueOf(-500.0), BigDecimal.valueOf(500.0), TransactionStatus.OK)

        then:
        thrown(InvalidTransactionException)
    }

    def "Should not create CardTransactionCreate with new balance less than zero"() {
        given:
        UUID id = UUID.randomUUID()
        CardNumber cardNumber = new CardNumber("1234567890123456")
        CardPassword cardPassword = new CardPassword("1234")
        BigDecimal balance = BigDecimal.valueOf(500.00)
        Card card = new Card(id, cardNumber, cardPassword, balance)

        when:
        new CardTransactionCreate(card, BigDecimal.valueOf(100.0), BigDecimal.valueOf(500.0), BigDecimal.valueOf(-500.0), TransactionStatus.OK)

        then:
        thrown(InvalidTransactionException)
    }

}
