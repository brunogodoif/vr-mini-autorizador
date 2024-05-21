package br.com.brunogodoif.vrminiautorizador.infrastructure.gateways

import br.com.brunogodoif.vrminiautorizador.application.domain.entity.*
import br.com.brunogodoif.vrminiautorizador.infrastructure.gateways.exceptions.CardNotFoundException
import br.com.brunogodoif.vrminiautorizador.infrastructure.mapper.CardMapper
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository
import org.mockito.Mockito
import spock.lang.Specification

class CardGatewaySpec extends Specification {

    CardRepository cardRepositoryMock
    CardMapper cardMapperMock
    CardGateway cardGateway

    def setup() {
        cardRepositoryMock = Mockito.mock(CardRepository.class)
        cardMapperMock = Mockito.mock(CardMapper.class)
        cardGateway = new CardGateway(cardRepositoryMock, cardMapperMock)
    }

    def 'Should create a new card successfully'() {
        given:
        CardCreate cardCreate = new CardCreate(new CardNumber("1234567890123456"), new CardPassword("1234"), BigDecimal.valueOf(100.00))
        CardEntity cardEntity = new CardEntity()
        cardEntity.setCardNumber(cardCreate.getCard().getNumber())
        cardEntity.setPassword(cardCreate.getCardPassword().getPassword())
        cardEntity.setBalance(cardCreate.getBalance());
        Card card = new Card(UUID.randomUUID(), cardCreate.getCard(), cardCreate.getCardPassword(), cardCreate.getBalance())
        Mockito.when(cardMapperMock.toEntity(cardCreate)).thenReturn(cardEntity)
        Mockito.when(cardRepositoryMock.save(cardEntity)).thenReturn(cardEntity)
        Mockito.when(cardMapperMock.toDomainObj(cardEntity)).thenReturn(card)

        when:
        Card result = cardGateway.createCard(cardCreate)

        then:
        assert result.getCardNumber().getNumber().equals(card.getCardNumber().getNumber())
        assert result.getCardPassword().getPassword().equals(card.getCardPassword().getPassword())
        assert result.getBalance().equals(card.getBalance())
    }

    def 'Should successfully retrieve a card given its number'() {
        setup:
        def cardNumberVal = "1234567890123456"
        def passwordVal = "1234"
        def initialBalance = BigDecimal.valueOf(500.00)
        def cardNumber = new CardNumber(cardNumberVal)
        def cardPassword = new CardPassword(passwordVal)

        def card = new Card(UUID.randomUUID(), cardNumber, cardPassword, initialBalance)
        def cardEntity = new CardEntity()
        cardEntity.setCardNumber(cardNumber.getNumber())
        cardEntity.setPassword(cardPassword.getPassword())
        cardEntity.setBalance(initialBalance)

        Mockito.when(cardRepositoryMock.findByCardNumber(cardNumber.getNumber())).thenReturn(Optional.of(cardEntity))
        Mockito.when(cardMapperMock.toDomainObj(cardEntity)).thenReturn(card)

        when:
        def cardResponse = cardGateway.getCard(cardNumber.getNumber())

        then:
        notThrown()
        cardResponse.getCardNumber().getNumber() == cardNumber.getNumber()
        cardResponse.getCardPassword().getPassword() == cardPassword.getPassword()
        cardResponse.getBalance() == initialBalance
    }

    def 'Should throw CardNotFoundException when retrieving a non-existent card'() {
        setup:
        def cardNumber = "1234567890123456"
        Mockito.when(cardRepositoryMock.findByCardNumber(cardNumber)).thenReturn(Optional.empty())

        when: "the getCard method is called"
        def cardResponse = cardGateway.getCard(cardNumber)

        then: "the card is retrieved successfully"
        thrown(CardNotFoundException)
    }


    def 'Should successfully check card existence given its number'() {
        setup:
        def cardNumber = "1234567890123456"

        Mockito.when(cardRepositoryMock.existsByCardNumber(cardNumber)).thenReturn(false)

        when: "the cardExists method is called"
        def exists = cardGateway.cardExists(cardNumber)

        then: "the method returns true"
        !exists
    }

    def 'Should indicate that a card does not exist when checking its existence'() {
        given:
        String cardNum = "1234567890123456"
        BigDecimal cardBalance = BigDecimal.valueOf(100.00)
        CardUpdateBalance cardUpdateBalance = new CardUpdateBalance(new CardNumber(cardNum), cardBalance)
        CardEntity cardEntity = new CardEntity()
        cardEntity.setCardNumber(cardNum)
        cardEntity.setBalance(cardBalance);
        Mockito.when(cardRepositoryMock.findByCardNumber(cardUpdateBalance.getCard().getNumber())).thenReturn(Optional.of(cardEntity))
        Mockito.when(cardRepositoryMock.save(cardEntity)).thenReturn(cardEntity)

        when:
        boolean result = cardGateway.updateBalance(cardUpdateBalance)

        then:
        result
    }

    def 'Should successfuly update the balance of a card'() {
        setup:
        def cardNumberVal = "1234567890123456"
        def cardNumber = new CardNumber(cardNumberVal)
        def amount = BigDecimal.valueOf(500.00)
        def updateBalance = new CardUpdateBalance(cardNumber, amount)

        Mockito.when(cardRepositoryMock.findByCardNumber(cardNumber.getNumber())).thenReturn(Optional.empty())

        when:
        cardGateway.updateBalance(updateBalance)

        then: "an exception is thrown"
        thrown(CardNotFoundException)
    }

}
