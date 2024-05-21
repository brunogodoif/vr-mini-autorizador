package br.com.brunogodoif.vrminiautorizador.application.usecases

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardNumber
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardPassword
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardNotFoundException
import org.mockito.Mockito
import spock.lang.Specification

class GetBalanceSpec extends Specification {

    CardGatewayInterface cardGateway
    GetBalance getBalance

    def setup() {
        cardGateway = Mockito.mock(CardGatewayInterface.class)
        getBalance = new GetBalance(cardGateway)
    }

    def "Should return correct balance when card exists"() {
        given:
        UUID cardId = UUID.randomUUID()
        String cardNum = "1234567890123456"
        String password = "1234"
        BigDecimal expectedBalance = new BigDecimal(100)
        CardNumber cardNumber = new CardNumber(cardNum)
        CardPassword cardPassword = new CardPassword(password)
        Card card = new Card(cardId, cardNumber, cardPassword, expectedBalance);
        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(true)
        Mockito.when(cardGateway.getCard(cardNum)).thenReturn(card)

        when:
        BigDecimal balance = getBalance.execute(cardNum)

        then:
        balance == expectedBalance
    }

    def "Should throw CardNotFoundException when card does not exist"() {
        given:
        String cardNum = "1234567890123456"
        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(false)

        when:
        getBalance.execute(cardNum)

        then:
        thrown(CardNotFoundException)
    }

}
