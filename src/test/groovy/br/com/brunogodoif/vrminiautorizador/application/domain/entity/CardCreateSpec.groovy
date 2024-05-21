package br.com.brunogodoif.vrminiautorizador.application.domain.entity


import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardCreateException
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardNumberException
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardPasswordException
import spock.lang.Specification

class CardCreateSpec extends Specification {

    def "Should correctly assign values from constructor in CardCreate"() {
        given:
        def cardNumber = new CardNumber("1234567890123456")
        def cardPassword = new CardPassword("1234")
        BigDecimal balance = new BigDecimal(500)

        when:
        def cardCreate = new CardCreate(cardNumber, cardPassword, balance)

        then:
        cardCreate.getCard() == cardNumber
        cardCreate.getCardPassword().getPassword() == cardPassword.getPassword()
        cardCreate.getBalance().compareTo(balance) == 0
    }

    def "Should correctly assign different values from constructor in CardCreate"() {
        given:
        def cardNumber = new CardNumber("1234567890123456")
        def cardPassword = new CardPassword("1234")
        BigDecimal balance = new BigDecimal(1000)

        when:
        def cardCreate = new CardCreate(cardNumber, cardPassword, balance)

        then:
        cardCreate.getCard() == cardNumber
        cardCreate.getCardPassword().getPassword() == cardPassword.getPassword()
        cardCreate.getBalance().compareTo(balance) == 0
    }

    def "Should throw InvalidCardNumberException when creating CardCreate with invalid card number"() {
        when:
        def cardNumber = new CardNumber("123")
        def cardPassword = new CardPassword("1234")
        BigDecimal balance = new BigDecimal(500)
        new CardCreate(cardNumber, cardPassword, balance)

        then:
        thrown(InvalidCardNumberException)
    }

    def "Should throw InvalidCardPasswordException when creating CardCreate with invalid card password"() {
        when:
        def cardNumber = new CardNumber("1234567890123456")
        def cardPassword = new CardPassword("xpto")
        BigDecimal balance = new BigDecimal(1000)
        new CardCreate(cardNumber, cardPassword, balance)

        then:
        thrown(InvalidCardPasswordException)
    }

    def "Should throw InvalidCardCreateException when creating CardCreate with invalid balance value"() {
        when:
        def cardNumber = new CardNumber("1234567890123456")
        def cardPassword = new CardPassword("1234")
        BigDecimal balance = BigDecimal.ZERO
        new CardCreate(cardNumber, cardPassword, balance)

        then:
        thrown(InvalidCardCreateException)
    }

}