package br.com.brunogodoif.vrminiautorizador.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
abstract class BaseIntegrationTest extends Specification {
    @Shared
    private static MySQLContainer<?> mysqlContainer = TestContainerManager.getContainer()

    def "MySQL container is running"() {
        expect:
        mysqlContainer.isRunning()
    }
}