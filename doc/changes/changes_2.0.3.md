# Virtual Schema for Hive 2.0.3, released 2023-03-20

Code name: Dependency Upgrade

## Summary

This release updates dependencies to remove references to discontinued maven repository `maven.exasol.com`.

## Changes

* #35: Updated dependencies

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:1.0.0` to `1.0.1`
* Updated `com.exasol:virtual-schema-common-jdbc:10.1.0` to `10.5.0`

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.1.16` to `7.1.17`
* Updated `com.exasol:exasol-testcontainers:6.4.0` to `6.5.1`
* Updated `com.exasol:test-db-builder-java:3.4.1` to `3.4.2`
* Updated `com.exasol:virtual-schema-common-jdbc:10.1.0` to `10.5.0`
* Updated `org.junit.jupiter:junit-jupiter:5.9.1` to `5.9.2`
* Updated `org.mockito:mockito-junit-jupiter:4.7.0` to `5.2.0`
* Updated `org.testcontainers:junit-jupiter:1.17.3` to `1.17.6`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.1` to `1.2.2`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.1` to `2.9.4`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.1.0` to `3.2.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M7` to `3.0.0-M8`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M7` to `3.0.0-M8`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.13.0` to `2.14.2`
