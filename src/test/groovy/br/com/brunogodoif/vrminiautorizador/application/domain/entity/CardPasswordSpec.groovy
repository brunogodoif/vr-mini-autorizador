package br.com.brunogodoif.vrminiautorizador.application.domain.entity

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardPasswordException
import spock.lang.Specification

class CardPasswordSpec extends Specification {
    def "Should create CardPassword with valid password"() {
        when:
        def cardPassword = new CardPassword("1234")

        then:
        cardPassword.getPassword() == "1234"
    }

    def "Should not create CardPassword with invalid password"() {
        when:
        new CardPassword("abcd")

        then:
        thrown(InvalidCardPasswordException)
    }
}