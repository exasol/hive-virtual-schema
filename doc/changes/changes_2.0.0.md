# Hive Virtual Schemas 2.0.0, released 2021-02-17

Code name: Removed `SQL_DIALECT` property

## Summary

The `SQL_DIALECT` property used when executing a `CREATE VIRTUAL SCHEMA` from the Exasol database is obsolete from this version. Please, do not provide this property anymore.

## Feature

* #7: Added new capabilities for scalar and aggregate functions. 

## Documentation

* #3: Added information about integration tests.
* #11: Changed the Hive connection name in user guide.

## Refactoring

* #9: Added error builder.
* #13: Updated dialect to the latest `virtual-schema-common-jdbc`.

## Dependencies Updates

### Runtime Dependencies

* Added `com.exasol:error-reporting-java:0.2.2`
* Updated `com.exasol:virtual-schema-common-jdbc:8.0.0` to `9.0.1`

### Test Dependencies

* Updated `com.exasol:exasol-testcontainers:3.3.1` to `3.5.0`
* Updated `com.exasol:hamcrest-resultset-matcher:1.2.2` to `1.4.0`
* Updated `com.exasol:test-db-builder-java:1.1.0` to `3.0.0`
* Updated `org.mockito:mockito-junit-jupiter:3.6.0` to `3.7.7`
* Updated `org.testcontainers:junit-jupiter:1.15.0` to `1.15.2`
* Updated `org.apache.hbase:hbase-server:2.4.0` to `2.4.1`
* Updated `org.junit.jupiter:junit-jupiter:5.7.0` to `5.7.1`
* Updated `com.exasol:exasol-jdbc:7.0.4` to `7.0.7`

### Plugin Dependencies

* Added `com.exasol:error-code-crawler-maven-plugin:0.1.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.5` to `0.8.6`