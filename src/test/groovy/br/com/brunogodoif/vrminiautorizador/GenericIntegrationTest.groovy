package br.com.brunogodoif.vrminiautorizador

import br.com.brunogodoif.vrminiautorizador.config.TestcontainersConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
@Testcontainers
@ContextConfiguration(classes = TestcontainersConfig)
class GenericIntegrationTest extends Specification {

    @Shared
    private static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.28")
            .withUsername("root")
            .withPassword("password")
            .withDatabaseName("test_db");

    def setupSpec() {
        mysqlContainer.start()
    }

    def cleanupSpec() {
        mysqlContainer.stop()
    }

    def "MySQL container is running"() {
        expect:
        mysqlContainer.isRunning()
    }
}