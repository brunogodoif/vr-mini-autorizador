package br.com.brunogodoif.vrminiautorizador.infrastructure.gateways

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.Card
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardNumber
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardPassword
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransaction
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.CardTransactionCreate
import br.com.brunogodoif.vrminiautorizador.application.domain.entity.TransactionStatus
import br.com.brunogodoif.vrminiautorizador.infrastructure.gateways.exceptions.CardNotFoundException
import br.com.brunogodoif.vrminiautorizador.infrastructure.mapper.CardTransactionMapper
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardTransactionEntity
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardTransactionRepository
import org.mockito.Mockito
import spock.lang.Specification

import java.time.LocalDateTime

class CardTransactionGatewaySpec extends Specification {

    CardRepository cardRepositoryMock
    CardTransactionRepository cardTransactionRepositoryMock
    CardTransactionMapper cardTransactionMapperMock
    CardTransactionGateway cardTransactionGateway

    def setup() {
        cardRepositoryMock = Mockito.mock(CardRepository.class)
        cardTransactionRepositoryMock = Mockito.mock(CardTransactionRepository.class)
        cardTransactionMapperMock = Mockito.mock(CardTransactionMapper.class)
        cardTransactionGateway = new CardTransactionGateway(cardRepositoryMock, cardTransactionRepositoryMock, cardTransactionMapperMock)
    }

    def 'Should successfully persist a card transaction'() {
        given:
        def cardNumber = new CardNumber("1234567890123456")
        def cardPassword = new CardPassword("1234")
        def balance = BigDecimal.valueOf(500.00)
        def card = new Card(UUID.randomUUID(), cardNumber, cardPassword, balance)

        def value = BigDecimal.valueOf(50.00)
        def previousBalance = balance
        def newBalance = balance.subtract(value)
        def status = TransactionStatus.OK

        def cardTransactionCreate = new CardTransactionCreate(card, value, previousBalance, newBalance, status)

        CardEntity cardEntity = new CardEntity()
        cardEntity.setCardNumber(cardNumber.getNumber())
        cardEntity.setPassword(cardPassword.getPassword())
        cardEntity.setBalance(balance)

        CardTransactionEntity cardTransactionEntity = new CardTransactionEntity()
        cardTransactionEntity.setCard(cardEntity)
        cardTransactionEntity.setValue(value)
        cardTransactionEntity.setPreviousBalance(previousBalance)
        cardTransactionEntity.setNewBalance(newBalance)
        cardTransactionEntity.setStatus(status.toString())
        cardTransactionEntity.setCreatedAt(LocalDateTime.now())
        cardTransactionEntity.setUpdatedAt(LocalDateTime.now())

        CardTransaction cardTransactionExpected = new CardTransaction(UUID.randomUUID(), card, value, previousBalance,
                newBalance, TransactionStatus.OK, LocalDateTime.now(), LocalDateTime.now())

        Mockito.when(cardRepositoryMock.findByCardNumber(cardNumber.getNumber())).thenReturn(Optional.of(cardEntity))
        Mockito.when(cardTransactionMapperMock.toEntity(cardTransactionCreate)).thenReturn(cardTransactionEntity)
        Mockito.when(cardTransactionRepositoryMock.save(cardTransactionEntity)).thenReturn(cardTransactionEntity)
        Mockito.when(cardTransactionMapperMock.toDomainObject(cardTransactionEntity)).thenReturn(cardTransactionExpected)

        when:
        CardTransaction result = cardTransactionGateway.persistTransaction(cardTransactionCreate)

        then:
        result.getId() == cardTransactionExpected.getId()
        result.getCard() == cardTransactionExpected.getCard()
        result.getValue() == cardTransactionExpected.getValue()
        result.getPreviousBalance() == cardTransactionExpected.getPreviousBalance()
        result.getNewBalance() == cardTransactionExpected.getNewBalance()
        result.getStatus() == cardTransactionExpected.status
    }

    def 'Should throw CardNotFoundException while persisting a card transaction when card is not found'() {
        given:
        def cardNumber = new CardNumber("1234567890123456")
        def cardPassword = new CardPassword("1234")
        def balance = BigDecimal.valueOf(500.00)
        def card = new Card(UUID.randomUUID(), cardNumber, cardPassword, balance)

        def value = BigDecimal.valueOf(50.00)
        def previousBalance = balance
        def newBalance = balance.subtract(value)
        def status = TransactionStatus.OK

        def cardTransactionCreate = new CardTransactionCreate(card, value, previousBalance, newBalance, status)

        Mockito.when(cardRepositoryMock.findByCardNumber(cardNumber.getNumber())).thenReturn(Optional.empty())

        when:
        cardTransactionGateway.persistTransaction(cardTransactionCreate)

        then:
        thrown(CardNotFoundException)
    }
}
