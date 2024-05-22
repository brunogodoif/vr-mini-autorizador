package br.com.brunogodoif.vrminiautorizador.config;

import org.testcontainers.containers.MySQLContainer;
import spock.lang.Shared;

public class TestContainerManager {
    @Shared
    private static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.28")
            .withUsername("root")
            .withPassword("password")
            .withDatabaseName("test_db");

    static {
        mysqlContainer.start();
        System.setProperty("spring.datasource.url", mysqlContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysqlContainer.getUsername());
        System.setProperty("spring.datasource.password", mysqlContainer.getPassword());
        System.setProperty("spring.datasource.driver-class-name", mysqlContainer.getDriverClassName());
    }

    static MySQLContainer<?> getContainer() {
        return mysqlContainer;
    }
}
