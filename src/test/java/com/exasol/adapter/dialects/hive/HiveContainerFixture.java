package com.exasol.adapter.dialects.hive;

import static com.exasol.adapter.dialects.IntegrationTestConstants.DOCKER_IP_ADDRESS;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

class HiveContainerFixture implements AutoCloseable {
    private static final int HIVE_EXPOSED_PORT = 10000;
    private static final String SCHEMA_HIVE = "default";
    private static final String HIVE_USERNAME = "hive";
    private static final String HIVE_PASSWORD = "hive";

    private static final File HIVE_DOCKER_COMPOSE_YAML = new File(
            "src/test/resources/integration/driver/hive/docker-compose.yaml");
    private static final String HIVE_SERVICE_NAME = "hiveserver2";

    private final ComposeContainer container;
    private final HiveJdbcDriverLoader jdbcDriverLoader;

    private HiveContainerFixture(final ComposeContainer container, final HiveJdbcDriverLoader jdbcDriverLoader) {
        this.container = container;
        this.jdbcDriverLoader = jdbcDriverLoader;
    }

    static HiveContainerFixture start() {
        final HiveContainerFixture fixture = new HiveContainerFixture(createContainer(), new HiveJdbcDriverLoader());
        fixture.container.start();
        return fixture;
    }

    @Override
    public void close() {
        try {
            this.container.close();
        } finally {
            this.jdbcDriverLoader.close();
        }
    }

    @SuppressWarnings("resource") // Container will be closed in close() method
    static ComposeContainer createContainer() {
        return new ComposeContainer(HIVE_DOCKER_COMPOSE_YAML)
                .withExposedService(HIVE_SERVICE_NAME, HIVE_EXPOSED_PORT,
                        Wait.forListeningPort().withStartupTimeout(Duration.ofMinutes(2)))
                .withEnv(dockerComposeEnvironment());
    }

    private static Map<String, String> dockerComposeEnvironment() {
        return Map.of(
                "HIVE_VERSION", "4.2.0",
                "POSTGRES_LOCAL_PATH", getPostgreSqlJdbcDriverPath(),
                "HIVE_ZOOKEEPER_QUORUM", "",
                "HIVE_WAREHOUSE_PATH", "/opt/hive/data/warehouse",
                "DEFAULT_FS", "file:///",
                "HIVE_EXECUTION_ENGINE", "mr",

                // Suppress warnings about unset variables
                "S3_ENDPOINT_URL", "",
                "AWS_ACCESS_KEY_ID", "",
                "AWS_SECRET_ACCESS_KEY", "");
    }

    String getExasolConnectionString() {
        return "jdbc:hive2://" + DOCKER_IP_ADDRESS + ":" + HIVE_EXPOSED_PORT + "/" + SCHEMA_HIVE;
    }

    Connection getHiveConnection() {
        final Driver driver = this.jdbcDriverLoader.load();
        final String url = "jdbc:hive2://localhost:" + HIVE_EXPOSED_PORT + "/" + SCHEMA_HIVE;
        try {
            return driver.connect(url, new Properties());
        } catch (final SQLException e) {
            throw new IllegalStateException("Could not connect to Hive container at " + url, e);
        }
    }

    private static String getPostgreSqlJdbcDriverPath() {
        final Path driverPath = Path.of("target/postgresql-jdbc-driver.jar").toAbsolutePath();
        if (!Files.isRegularFile(driverPath)) {
            throw new IllegalStateException(
                    "PostgreSQL JDBC driver not found at " + driverPath.toAbsolutePath()
                            + ". Ensure that maven-dependency-plugin has copied the driver to the target directory.");
        }
        return driverPath.toString();
    }

    String getUser() {
        return HIVE_USERNAME;
    }

    String getPassword() {
        return HIVE_PASSWORD;
    }

    String getSchema() {
        return SCHEMA_HIVE;
    }
}
