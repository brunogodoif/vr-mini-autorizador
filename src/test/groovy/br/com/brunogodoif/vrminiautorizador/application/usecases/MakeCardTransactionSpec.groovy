package br.com.brunogodoif.vrminiautorizador.application.usecases

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardNumber
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardPassword
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardGatewayInterface
import br.com.brunogodoif.vrminiautorizador.application.gateways.CardTransactionGatewayInterface
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.CardNotFoundException
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.InsufficientBalanceException
import br.com.brunogodoif.vrminiautorizador.application.usecases.exceptions.InvalidPasswordException
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardTransactionRequest
import org.mockito.Mockito
import spock.lang.Specification

class MakeCardTransactionSpec extends Specification {

    CardGatewayInterface cardGateway
    CardTransactionGatewayInterface cardTransactionGateway
    MakeCardTransaction makeCardTransaction

    def setup() {
        cardGateway = Mockito.mock(CardGatewayInterface.class)
        cardTransactionGateway = Mockito.mock(CardTransactionGatewayInterface.class)
        makeCardTransaction = new MakeCardTransaction(cardGateway, cardTransactionGateway)
    }

    def "Should make transaction if card exists and balance is sufficient"() {
        given:
        String cardNum = "1234567890123456"
        String password = "1234"
        BigDecimal transactionValue = BigDecimal.valueOf(50.00)
        BigDecimal cardBalance = BigDecimal.valueOf(100.00)
        CardTransactionRequest cardTransactionRequest = new CardTransactionRequest(cardNum, password, transactionValue.doubleValue())
        Card card = new Card(UUID.randomUUID(), new CardNumber(cardNum), new CardPassword(password), cardBalance)
        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(true)
        Mockito.when(cardGateway.getCard(cardNum)).thenReturn(card)

        when:
        def transactionMade = makeCardTransaction.execute(cardTransactionRequest)

        then:
        noExceptionThrown()
        transactionMade
    }

    def "Should throw CardNotFoundException if card does not exist"() {
        given:
        String cardNum = "1234567890123456"
        String password = "1234"
        BigDecimal transactionValue = BigDecimal.valueOf(50.00)
        CardTransactionRequest cardTransactionRequest = new CardTransactionRequest(cardNum, password, transactionValue.doubleValue())
        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(false)

        when:
        makeCardTransaction.execute(cardTransactionRequest)

        then:
        thrown(CardNotFoundException)
    }

    def "Should throw InvalidPasswordException if password is incorrect"() {
        given:
        String cardNum = "1234567890123456"
        String correctPassword = "1234"
        String incorrectPassword = "1111"
        BigDecimal transactionValue = BigDecimal.valueOf(50.00)
        BigDecimal cardBalance = BigDecimal.valueOf(100.00)
        CardTransactionRequest cardTransactionRequest = new CardTransactionRequest(cardNum, incorrectPassword, transactionValue.doubleValue())
        Card card = new Card(UUID.randomUUID(), new CardNumber(cardNum), new CardPassword(correctPassword), cardBalance)
        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(true)
        Mockito.when(cardGateway.getCard(cardNum)).thenReturn(card)

        when:
        makeCardTransaction.execute(cardTransactionRequest)

        then:
        thrown(InvalidPasswordException)
    }

    def "Should throw InsufficientBalanceException if balance is insufficient"() {
        given:
        String cardNum = "1234567890123456"
        String password = "1234"
        BigDecimal transactionValue = BigDecimal.valueOf(200.00)
        BigDecimal cardBalance = BigDecimal.valueOf(100.00)
        CardTransactionRequest cardTransactionRequest = new CardTransactionRequest(cardNum, password, transactionValue.doubleValue())
        Card card = new Card(UUID.randomUUID(), new CardNumber(cardNum), new CardPassword(password), cardBalance)
        Mockito.when(cardGateway.cardExists(cardNum)).thenReturn(true)
        Mockito.when(cardGateway.getCard(cardNum)).thenReturn(card)

        when:
        makeCardTransaction.execute(cardTransactionRequest)

        then:
        thrown(InsufficientBalanceException)
    }


}
