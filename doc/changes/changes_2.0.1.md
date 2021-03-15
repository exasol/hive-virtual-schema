# Hive Virtual Schemas 2.0.1, released 2021-??-??

Code name:

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