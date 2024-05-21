package br.com.brunogodoif.vrminiautorizador.application.domain.entity

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardNumberException
import spock.lang.Specification

class CardNumberSpec extends Specification {

    def "Should create CardNumber with valid number"() {
        when:
        def cardNumber = new CardNumber("1234567890123456")

        then:
        cardNumber.getNumber() == "1234567890123456"
    }

    def "Should not create CardNumber with invalid number"() {
        when:
        new CardNumber("123")

        then:
        thrown(InvalidCardNumberException)
    }

}