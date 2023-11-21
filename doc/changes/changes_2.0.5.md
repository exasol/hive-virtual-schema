# Virtual Schema for Hive 2.0.5, released 2023-11-21

Code name: Fix CVE-2023-4043 in test dependency `org.eclipse.parsson:parsson`

## Summary

This release fixes vulnerability CVE-2023-4043 in test dependency `org.eclipse.parsson:parsson`.

## Security

* #48: Fixed CVE-2023-4043 in test dependency `org.eclipse.parsson:parsson`

## Documentation

* #40: Added missing `--/` to documentation

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.1.17` to `7.1.20`
* Updated `com.exasol:exasol-testcontainers:6.6.1` to `6.6.3`
* Updated `com.exasol:hamcrest-resultset-matcher:1.5.2` to `1.6.3`
* Updated `com.exasol:test-db-builder-java:3.4.2` to `3.5.2`
* Updated `org.apache.commons:commons-compress:1.24.0` to `1.25.0`
* Updated `org.junit.jupiter:junit-jupiter:5.9.2` to `5.10.1`
* Updated `org.mockito:mockito-junit-jupiter:5.2.0` to `5.7.0`
* Added `org.slf4j:slf4j-jdk14:2.0.9`
* Updated `org.testcontainers:junit-jupiter:1.17.6` to `1.19.2`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.3.0` to `1.3.1`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.11` to `2.9.16`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.1.2` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.1.2` to `3.2.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.0` to `2.16.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.10` to `0.8.11`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184` to `3.10.0.2594`
