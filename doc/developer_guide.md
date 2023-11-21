# Developer Guide

## Executing Disabled Integration Tests

The integration tests for this repository are disabled, but it is possible to execute them locally. 	
The reason for the tests being disabled is we can only deliver drivers where the license allows redistribution.

### Starting Disabled Integration Test Locally

1. Download the [Hive JDBC driver `HiveJDBC42.jar`](https://www.cloudera.com/downloads/connectors/hive/jdbc/2-6-23.html)
2. Temporarily put the driver into `src/test/resources/integration/driver/hive` directory.
3. Make sure that the file's name is `HiveJDBC42.jar`.
4. Run integration tests:
   * Run `HiveSqlDialectIT` from your IDE or
   * temporarily comment out the `skip` property of `maven-failsafe-plugin` and execute `mvn verify` command.
5. **Do not upload the driver to the GitHub repository**.
