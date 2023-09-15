# Virtual Schema for Hive 2.0.4, released 2023-09-15

Code name: Update to the latest `virtual-schema-common-jdbc`

## Summary

This release updates Hive virtual scheme connector to the latest 
`virtual-schema-common-jdbc` which fixes JDBC connection issues.

## Features

* #41: Update to the latest `virtual-schema-common` libs
* #44: Update to the latest `virtual-schema-common-jdbc`, update test deps to fix vulnerability (CVE-2023-42503)

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:virtual-schema-common-jdbc:10.5.0` to `11.0.2`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.5.1` to `6.6.1`
* Updated `com.exasol:virtual-schema-common-jdbc:10.5.0` to `11.0.2`
* Added `org.apache.commons:commons-compress:1.24.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.2` to `1.3.0`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.4` to `2.9.11`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.2.1` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M8` to `3.1.2`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M8` to `3.1.2`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:2.0.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.3.0` to `1.5.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.14.2` to `2.16.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.10`
