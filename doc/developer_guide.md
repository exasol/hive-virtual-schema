# Developer Guide

## Executing Disabled Integration Tests

The integration tests for this repository are disabled, but it is possible to execute them locally. 	
The reason for the tests being disabled is we can only deliver drivers where the license allows redistribution.
See the [user guide](user_guide/hive_user_guide.md#difference-between-apache-jdbc-and-cloudera-jdbc-drivers) why we can't use the Apache Hive JDBC driver.

### Starting Disabled Integration Test Locally

1. Download the [Hive JDBC driver `HiveJDBC42.jar`](https://www.cloudera.com/downloads/connectors/hive/jdbc/2-6-30.html). You need to fill the form in order to download the driver.
2. Temporarily put the driver into `src/test/resources/integration/driver/hive` directory.
3. Make sure that the file's name is `HiveJDBC42.jar`.
4. Run integration tests:
   * Run `HiveSqlDialectIT` from your IDE or
   * Run `mvn verify -DskipIntegrationTests=false`
5. **Do not upload the driver to the GitHub repository**.
