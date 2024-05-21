package br.com.brunogodoif.vrminiautorizador.application.domain.entity


import spock.lang.Specification

class CardSpec extends Specification {

    def "Card full constructor sets properties correctly"() {
        given:
        UUID id = UUID.randomUUID()
        CardNumber cardNumber = new CardNumber("1234567890123456")
        CardPassword cardPassword = new CardPassword("1234")
        BigDecimal balance = BigDecimal.valueOf(100.00)

        when:
        Card card = new Card(id, cardNumber, cardPassword, balance)

        then:
        card.id == id
        card.cardNumber == cardNumber
        card.cardPassword == cardPassword
        card.balance == balance
    }

    def "Card constructor without ID sets properties correctly"() {
        given:
        CardNumber cardNumber = new CardNumber("1234567890123456")
        CardPassword cardPassword = new CardPassword("1234")

        when:
        Card card = new Card(cardNumber, cardPassword)

        then:
        card.cardNumber == cardNumber
        card.cardPassword == cardPassword
    }

}
