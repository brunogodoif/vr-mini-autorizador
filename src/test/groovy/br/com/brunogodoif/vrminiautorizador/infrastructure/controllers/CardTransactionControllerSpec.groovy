package br.com.brunogodoif.vrminiautorizador.infrastructure.controllers

import br.com.brunogodoif.vrminiautorizador.config.BaseIntegrationTest
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardTransactionRequest
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Shared

import java.time.LocalDateTime

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@AutoConfigureMockMvc
class CardTransactionControllerSpec extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private CardRepository cardRepository

    @Shared
            defaultBalanceCard = new BigDecimal("500.00")

    def cleanup() {
        cleanupDatabaseCardTransaction()
    }

    def "Should make a transaction successfully"() {
        setup:
        setupDatabaseCardTransaction();
        def transactionRequest = getCardTransactionCreateRequest();

        when:
        def result = performPostRequest("/transacoes", transactionRequest);

        then:
        assertResponseStatus(result, 201);
    }

    def "Should make a transaction fail, invalid card number"() {
        setup:
        def transactionRequest = getCardTransactionCreateInvalidCardNumberRequest();

        when:
        def result = performPostRequest("/transacoes", transactionRequest);

        then:
        assertResponseStatus(result, 400);
    }

    def "Should make a transaction fail, invalid card password"() {
        setup:
        def transactionRequest = getCardTransactionCreateInvalidCardPasswordRequest();

        when:
        def result = performPostRequest("/transacoes", transactionRequest);

        then:
        assertResponseStatus(result, 400);
    }

    def "Should make a transaction fail, invalid transaction value"() {
        setup:
        def transactionRequest = getCardTransactionCreateInvalidValueRequest();

        when:
        def result = performPostRequest("/transacoes", transactionRequest);

        then:
        assertResponseStatus(result, 400);
    }

    def "Should make a transaction fail, card number not found in database"() {
        setup:
        def transactionRequest = getCardTransactionCreateRequest();

        when:
        def result = performPostRequest("/transacoes", transactionRequest);

        then:
        assertResponseStatus(result, 422);
    }

    def "Should make a transaction fail, card password different from database record"() {
        setup:
        setupDatabaseCardTransaction();
        def transactionRequest = getCardTransactionCreateWithDiffPasswordDatabaseRequest();

        when:
        def result = performPostRequest("/transacoes", transactionRequest);

        then:
        assertResponseStatus(result, 422);
    }

    def "Should make a transaction fail, insufficient funds in the card"() {
        setup:
        setupDatabaseCardTransactionInsufficientFunds();
        def transactionRequest = getCardTransactionCreateRequest();

        when:
        def result = performPostRequest("/transacoes", transactionRequest);

        then:
        assertResponseStatus(result, 422);
    }

    private MvcResult performPostRequest(String url, String content) {
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andReturn()
    }

    private void assertResponseStatus(MvcResult result, int expectedStatus) {
        result.getResponse().getStatus() == expectedStatus
    }

    private static String convertToJsonString(Object object) {
        return new ObjectMapper().writeValueAsString(object);
    }

    private static CardTransactionRequest createCardTransactionRequest(String cardNumber, String cardPassword, double value) {
        return new CardTransactionRequest(cardNumber, cardPassword, value);
    }

    static def getCardTransactionCreateRequest() {
        return convertToJsonString(createCardTransactionRequest("1234567890123456", "1234", 100.00));
    }

    static def getCardTransactionCreateInvalidCardNumberRequest() {
        return convertToJsonString(createCardTransactionRequest("1234", "1234", 100.00));
    }

    static def getCardTransactionCreateInvalidCardPasswordRequest() {
        return convertToJsonString(createCardTransactionRequest("1234567890123456", "123123", 100.00));
    }

    static def getCardTransactionCreateInvalidValueRequest() {
        return convertToJsonString(createCardTransactionRequest("1234567890123456", "1234", 0));
    }

    static def getCardTransactionCreateWithDiffPasswordDatabaseRequest() {
        return convertToJsonString(createCardTransactionRequest("1234567890123456", "4321", 100.00));
    }

    def setupDatabaseCardTransaction() {
        def cardEntity = new CardEntity(
                cardNumber: "1234567890123456",
                password: "1234",
                balance: defaultBalanceCard,
                createdAt: LocalDateTime.now(),
                updatedAt: LocalDateTime.now()
        )
        cardRepository.save(cardEntity)
    }

    def setupDatabaseCardTransactionInsufficientFunds() {
        def cardEntity = new CardEntity(
                cardNumber: "1234567890123456",
                password: "1234",
                balance: BigDecimal.ZERO,
                createdAt: LocalDateTime.now(),
                updatedAt: LocalDateTime.now()
        )
        cardRepository.save(cardEntity)
    }

    def cleanupDatabaseCardTransaction() {
        cardRepository.deleteAll()
    }

}