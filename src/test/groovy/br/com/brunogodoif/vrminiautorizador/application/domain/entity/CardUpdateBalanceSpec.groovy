package br.com.brunogodoif.vrminiautorizador.application.domain.entity

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidBalanceUpdateException
import spock.lang.Specification

class CardUpdateBalanceSpec extends Specification {

    def "Should create CardUpdateBalance with valid card and amount"() {
        given:
        def cardNumber = new CardNumber("1234567890123456")

        when:
        def updateBalance = new CardUpdateBalance(cardNumber, BigDecimal.valueOf(100.0))

        then:
        updateBalance.getCard() == cardNumber
        updateBalance.getAmount().compareTo(BigDecimal.valueOf(100.0)) == 0
    }

    def "Should not create CardUpdateBalance with amount less to zero"() {
        given:
        def cardNumber = new CardNumber("1234567890123456")

        when:
        new CardUpdateBalance(cardNumber, BigDecimal.valueOf(-100.0))

        then:
        thrown(InvalidBalanceUpdateException)
    }

}
