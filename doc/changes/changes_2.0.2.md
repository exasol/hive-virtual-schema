# Virtual Schema for Hive 2.0.2, released 2022-12-07

Code name: Updated User Guide with Additional Kerberos Information

## Summary

In this release, we update user-guide with additional Kerberos setup information and upgraded versions of dependencies.

## Documentation

* #31: Updated reference to `create_kerberos_conn.py` file in user guide
* #32: Updated user-guide with additional information on Kerberos setup

## Bugfixes

* #26: Fixed broken links checker

## Refactoring

* #23: Updated dependencies

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.0` to `1.0.0`
* Updated `com.exasol:virtual-schema-common-jdbc:9.0.3` to `10.1.0`

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.0.11` to `7.1.16`
* Updated `com.exasol:exasol-testcontainers:4.0.0` to `6.4.0`
* Updated `com.exasol:hamcrest-resultset-matcher:1.4.1` to `1.5.2`
* Updated `com.exasol:test-db-builder-java:3.2.1` to `3.4.1`
* Updated `com.exasol:virtual-schema-common-jdbc:9.0.3` to `10.1.0`
* Updated `org.junit.jupiter:junit-jupiter:5.7.2` to `5.9.1`
* Updated `org.mockito:mockito-junit-jupiter:3.11.2` to `4.7.0`
* Updated `org.testcontainers:junit-jupiter:1.16.0` to `1.17.3`

### Plugin Dependency Updates

* Updated `com.exasol:artifact-reference-checker-maven-plugin:0.3.1` to `0.4.2`
* Updated `com.exasol:error-code-crawler-maven-plugin:0.4.0` to `1.2.1`
* Updated `com.exasol:project-keeper-maven-plugin:0.10.0` to `2.9.1`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.13` to `0.16`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.3.0` to `3.4.2`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.1.0` to `2.5`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.8.1` to `3.10.1`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.0.0-M1` to `2.7`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3` to `3.1.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M4` to `3.0.0-M7`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.0.0-M1` to `2.4`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.2.0` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.2.0` to `2.6`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.9.1` to `3.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M4` to `3.0.0-M7`
* Added `org.codehaus.mojo:flatten-maven-plugin:1.3.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.8.1` to `2.13.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.7` to `0.8.8`
* Added `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184`
* Updated `org.sonatype.ossindex.maven:ossindex-maven-plugin:3.1.0` to `3.2.0`
