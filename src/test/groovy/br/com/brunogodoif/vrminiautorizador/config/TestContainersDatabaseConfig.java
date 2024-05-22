package br.com.brunogodoif.vrminiautorizador.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@TestConfiguration
@Testcontainers
public class TestContainersDatabaseConfig {

    @Container
    private final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.28")
            .withUsername("root")
            .withPassword("password")
            .withDatabaseName("test_db");

    @Bean(initMethod = "start", destroyMethod = "stop")
    public MySQLContainer<?> mysqlContainer() {
        return mysqlContainer;
    }

    public String getJdbcUrl() {
        return mysqlContainer.getJdbcUrl();
    }

    public String getUsername() {
        return mysqlContainer.getUsername();
    }

    public String getPassword() {
        return mysqlContainer.getPassword();
    }
}
