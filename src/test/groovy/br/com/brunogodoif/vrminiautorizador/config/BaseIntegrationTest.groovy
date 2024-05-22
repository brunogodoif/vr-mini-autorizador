package br.com.brunogodoif.vrminiautorizador.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
@Testcontainers
@ContextConfiguration(classes = TestContainersDatabaseConfig)
@ActiveProfiles("test")
abstract class BaseIntegrationTest extends Specification {
    @Shared
    private static MySQLContainer<?> mysqlContainer

    def setupSpec() {
        if (mysqlContainer == null) {
            mysqlContainer = new MySQLContainer<>("mysql:8.0.28")
                    .withUsername("root")
                    .withPassword("password")
                    .withDatabaseName("test_db")
            mysqlContainer.start()
            System.getProperties()
                    .putAll([
                            "spring.datasource.url"              : mySQLContainer.getJdbcUrl(),
                            "spring.datasource.username"         : mySQLContainer.getUsername(),
                            "spring.datasource.password"         : mySQLContainer.getPassword(),
                            "spring.datasource.driver-class-name": mySQLContainer.getDriverClassName(),
                    ])
        }
    }

    def cleanupSpec() {
        mysqlContainer.stop()
    }

    def getMySQLContainer() {
        return mysqlContainer
    }

    def "MySQL container is running"() {
        expect:
        mysqlContainer.isRunning()
    }
}