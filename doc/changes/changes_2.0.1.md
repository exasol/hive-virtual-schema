# Hive Virtual Schemas 2.0.1, released 2021-08-12

Code name: Dependency Updates

## Summary

In this release we updated the dependencies. By that we fixed transitive CVE-2021-36090.


## Refactoring

* #16: Removed detected vulnerabilities in transitive dependencies.
* #19: Removed all Hive dependencies from pom.xml file and added runtime loading for Hive JDBC driver.

## Dependencies Updates

### Runtime Dependencies

* Updated `org.mockito:mockito-junit-jupiter:jar:3.7.7` to `3.8.0`
* Updated `com.exasol:exasol-testcontainers:jar:3.5.0` to `3.5.1`
* Updated `com.exasol:test-db-builder-java:jar:3.0.0` to `3.1.0`

### Test Dependencies

* Removed `org.apache.hive:hive-jdbc`
* Removed `org.apache.hbase:hbase-server`
* Removed `org.apache.httpcomponents:httpclient`
* Removed `org.apache.thrift:libthrift`

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.2.2` to `0.4.0`
* Updated `com.exasol:virtual-schema-common-jdbc:9.0.1` to `9.0.3`

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.0.7` to `7.0.11`
* Updated `com.exasol:exasol-testcontainers:3.5.0` to `4.0.0`
* Updated `com.exasol:hamcrest-resultset-matcher:1.4.0` to `1.4.1`
* Updated `com.exasol:test-db-builder-java:3.0.0` to `3.2.1`
* Updated `com.exasol:virtual-schema-common-jdbc:9.0.1` to `9.0.3`
* Removed `org.apache.hbase:hbase-server:2.4.1`
* Removed `org.apache.hive:hive-jdbc:3.1.2`
* Removed `org.apache.httpcomponents:httpclient:4.5.13`
* Removed `org.apache.thrift:libthrift:0.13.0`
* Updated `org.junit.jupiter:junit-jupiter:5.7.1` to `5.7.2`
* Updated `org.mockito:mockito-junit-jupiter:3.7.7` to `3.11.2`
* Updated `org.testcontainers:junit-jupiter:1.15.2` to `1.16.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:0.1.1` to `0.4.0`
* Updated `com.exasol:project-keeper-maven-plugin:0.4.2` to `0.10.0`
* Added `io.github.zlika:reproducible-build-maven-plugin:0.13`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.6` to `0.8.7`
