package br.com.brunogodoif.vrminiautorizador.application.usecases

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardCreate
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardNumber
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardPassword
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardCreateException
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardNumberException
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.exceptions.InvalidCardPasswordException
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardDuplicateException
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest
import org.mockito.Mockito
import spock.lang.Specification

class CreateCardSpec extends Specification {

    CardGatewayInterface cardGateway
    CreateCard createCard

    def setup() {
        cardGateway = Mockito.mock(CardGatewayInterface.class)
        createCard = new CreateCard(cardGateway)
    }

    def "Should create card when card does not exist"() {
        given:
        UUID cardId = UUID.randomUUID()
        String cardNum = "1234567890123456"
        String password = "1234"
        CardNumber cardNumber = new CardNumber(cardNum)
        CardPassword cardPassword = new CardPassword(password)
        BigDecimal defaultBalance = BigDecimal.valueOf(100.00)
        CardCreate cardToCreate = new CardCreate(cardNumber, cardPassword, defaultBalance)
        CardCreateRequest cardCreateRequest = new CardCreateRequest(cardNum, password)

        createCard.defaultCardBalance = defaultBalance

        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(false)
        Mockito.when(cardGateway.createCard(Mockito.any(CardCreate.class)))
                .thenReturn(new Card(cardId, cardNumber, cardPassword, defaultBalance))

        when:
        def cardCrated = createCard.execute(cardCreateRequest)

        then:
        notThrown()
        assert cardCrated.numeroCartao() == cardToCreate.getCard().getNumber()
        assert cardCrated.senha() == cardToCreate.getCardPassword().getPassword()
    }

    def "Should throw CardDuplicateException when card exists"() {
        given:
        String cardNum = "1234567890123456"
        String password = "1234"
        CardCreateRequest cardCreateRequest = new CardCreateRequest(cardNum, password)
        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(true)

        when:
        createCard.execute(cardCreateRequest)

        then:
        thrown(CardDuplicateException)
    }

    def "Should throw InvalidCardNumberException when card number is invalid"() {
        given:
        String cardNum = "1234"
        String password = "1234"
        CardCreateRequest cardCreateRequest = new CardCreateRequest(cardNum, password)

        when:
        createCard.execute(cardCreateRequest)

        then:
        thrown(InvalidCardNumberException)
    }

    def "Should throw InvalidCardPasswordException when card password is invalid"() {
        given:
        String cardNum = "1234567890123456"
        String password = "123"
        CardCreateRequest cardCreateRequest = new CardCreateRequest(cardNum, password)

        when:
        createCard.execute(cardCreateRequest)

        then:
        thrown(InvalidCardPasswordException)
    }

    def "Should throw InvalidCardCreateException when card create information is invalid"() {
        given:
        String cardNum = "1234567890123456"
        String password = "1234"
        CardCreateRequest cardCreateRequest = new CardCreateRequest(cardNum, password)
        createCard.defaultCardBalance = BigDecimal.ZERO

        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(false)

        when:
        createCard.execute(cardCreateRequest)

        then:
        thrown(InvalidCardCreateException)
    }

}
