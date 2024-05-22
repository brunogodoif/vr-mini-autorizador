package br.com.brunogodoif.vrminiautorizador.infrastructure.controllers

import br.com.brunogodoif.vrminiautorizador.config.BaseIntegrationTest
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request.CardCreateRequest
import br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.response.CardCreatedResponse
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.entities.CardEntity
import br.com.brunogodoif.vrminiautorizador.infrastructure.persistence.repositories.CardRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Shared

import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@AutoConfigureMockMvc
class CardControllerSpec extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private CardRepository cardRepository

    @Shared
            defaultBalanceCard = new BigDecimal("500.00")

    def cleanup() {
        cleanupDatabaseCardTransaction()
    }

    def "Should create a new card successfully"() {
        given:
        def request = getCardCreateValidRequest()
        def expectedResponse = new CardCreatedResponse("1234567890123456", "1234")

        when:
        def result = performPostRequest("/cartoes", request)

        then:
        assertResponseStatus(result, 201)

        ObjectMapper objectMapper = new ObjectMapper()
        def createdResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                CardCreatedResponse.class
        )

        createdResponse.numeroCartao() == expectedResponse.numeroCartao()
        createdResponse.senha() == expectedResponse.senha()

    }

    def "Should fail to create a new card, invalid card number"() {
        given:
        def request = getCardCreateInvalidCardNumberRequest()

        when:
        def result = performPostRequest("/cartoes", request)

        then:
        assertResponseStatus(result, 400)
    }

    def "Should fail to create a new card, invalid card password"() {
        given:
        def request = getCardCreateInvalidCardPasswordRequest()

        when:
        def result = performPostRequest("/cartoes", request)

        then:
        assertResponseStatus(result, 400)
    }

    def "Should fail to create a new card, card already registered"() {
        setup:
        setupDatabaseCard()
        def request = getCardCreateValidRequest()

        when:
        def result = performPostRequest("/cartoes", request)

        then:
        assertResponseStatus(result, 422)
    }


    def "Should get card balance successfully"() {
        setup:
        setupDatabaseCardGetBalance()
        def numeroCartao = "1212121212121212"

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/cartoes/{numeroCartao}", numeroCartao)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then:
        assertResponseStatus(result, 200)
        result.response.getContentAsString() == defaultBalanceCard.toString()
    }

    def "Should get card balance, invalid card number"() {
        given:
        def numeroCartao = "1234"

        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/cartoes/{numeroCartao}", numeroCartao)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then:
        assertResponseStatus(result, 400)
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

    private static CardCreateRequest createCardCreateRequest(String cardNumber, String password) {
        return new CardCreateRequest(cardNumber, password);
    }

    static def getCardCreateValidRequest() {
        return convertToJsonString(createCardCreateRequest("1234567890123456", "1234"));
    }

    static def getCardCreateInvalidCardNumberRequest() {
        return convertToJsonString(createCardCreateRequest("1234", "1234"));
    }

    static def getCardCreateInvalidCardPasswordRequest() {
        return convertToJsonString(createCardCreateRequest("1234567890123456", "123123"));
    }

    def setupDatabaseCard() {
        def cardEntity = new CardEntity(
                cardNumber: "1234567890123456",
                password: "1234",
                balance: defaultBalanceCard,
                createdAt: LocalDateTime.now(),
                updatedAt: LocalDateTime.now()
        )
        cardRepository.save(cardEntity)
    }

    def setupDatabaseCardGetBalance() {
        def cardEntity = new CardEntity(
                cardNumber: "1212121212121212",
                password: "1234",
                balance: defaultBalanceCard,
                createdAt: LocalDateTime.now(),
                updatedAt: LocalDateTime.now()
        )
        cardRepository.save(cardEntity)
    }

    def cleanupDatabaseCardTransaction() {
        cardRepository.deleteAll()
    }

}
