# Hive Virtual Schema

[![Build Status](https://travis-ci.com/exasol/hive-virtual-schema.svg?branch=main)](https://travis-ci.com/exasol/hive-virtual-schema)

SonarCloud results:

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Ahive-virtual-schema&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Ahive-virtual-schema)

# Overview

The **Hive Virtual Schema** provides an abstraction layer that makes an external [Hive](https://hive.apache.org/) accessible from an Exasol database through regular SQL commands. The contents of the external Hive database are mapped to virtual tables which look like and can be queried as any regular Exasol table.

If you want to set up a Virtual Schema for a different database system, please head over to the [Virtual Schemas Repository][virtual-schemas].

## Features

* Access a Hive database in read only mode from an Exasol database, using a Virtual Schema.

## Table of Contents

### Information for Users

* [Virtual Schemas User Guide][virtual-schemas-user-guide]
* [Hive Dialect User Guide](doc/user_guide/hive_user_guide.md)
* [Changelog](doc/changes/changelog.md)

Find all the documentation in the [Virtual Schemas project][vs-doc].

## Information for Developers

* [Virtual Schema API Documentation][vs-api]

## Dependencies

### Run Time Dependencies

Running the Virtual Schema requires a Java Runtime version 11 or later.

| Dependency                                          | Purpose                                                | License                    |
|-----------------------------------------------------|--------------------------------------------------------|--------------------------- |
| [Exasol Virtual Schema JDBC][vs-common-jdbc]        | Common JDBC functions for Virtual Schemas adapters     | MIT License                |
| [Hive JDBC driver][hive-jdbc-driver]                | Connecting to the data source                          | Check driver documentation |
| [Exasol Error Reporting Java][error-reporting-java] | Unified error messages.                                | MIT License                |

### Test Dependencies

| Dependency                                           | Purpose                                                | License                    |
|------------------------------------------------------|--------------------------------------------------------|----------------------------|
| [Apache Maven](https://maven.apache.org/)            | Build tool                                             | Apache License 2.0         |
| [Apache Trift][apache-trift]                         | Need for Hive integration test                         | Apache License 2.0         |
| [Exasol JDBC Driver][exasol-jdbc-driver]             | JDBC driver for Exasol database                        | MIT License                |
| [Exasol Testcontainers][exasol-testcontainers]       | Exasol extension for the Testcontainers framework      | MIT License                |
| [HBase server][hbase-server]                         | The Hadoop database                                    | Apache License 2.0         |
| [Hive JDBC Driver][hive-jdbc-driver]                 | JDBC driver for Hive database                          | Apache License 2.0         |
| [Java Hamcrest](http://hamcrest.org/JavaHamcrest/)   | Checking for conditions in code via matchers           | BSD License                |
| [JUnit](https://junit.org/junit5)                    | Unit testing framework                                 | Eclipse Public License 1.0 |
| [Mockito](http://site.mockito.org/)                  | Mocking framework                                      | MIT License                |
| [Testcontainers](https://www.testcontainers.org/)    | Container-based integration tests                      | MIT License                |
| [Test Database Builder][test-bd-builder]             | Fluent database interfaces for testing                 | MIT License                |

### Maven Plug-ins

| Plug-in                                                            | Purpose                                                | License                       |
|--------------------------------------------------------------------|--------------------------------------------------------|-------------------------------|
| [Maven Jacoco Plugin][maven-jacoco-plugin]                         | Code coverage metering                                 | Eclipse Public License 2.0    |
| [Maven Surefire Plugin][maven-surefire-plugin]                     | Unit testing                                           | Apache License 2.0            |
| [Maven Compiler Plugin][maven-compiler-plugin]                     | Setting required Java version                          | Apache License 2.0            |
| [Maven Assembly Plugin][maven-assembly-plugin]                     | Creating JAR                                           | Apache License 2.0            |
| [Maven Failsafe Plugin][maven-failsafe-plugin]                     | Integration testing                                    | Apache License 2.0            |
| [Versions Maven Plugin][versions-maven-plugin]                     | Checking if dependencies updates are available         | Apache License 2.0            |
| [Maven Enforcer Plugin][maven-enforcer-plugin]                     | Controlling environment constants                      | Apache License 2.0            |
| [Maven Dependency Plugin][maven-dependency-plugin]                 | Accessing to test dependencies                         | Apache License 2.0            |
| [Artifact Reference Checker Plugin][artifact-ref-checker-plugin]   | Check if artifact is referenced with correct version   | MIT License                   |
| [Project Keeper Maven Plugin][project-keeper-maven-plugin]         | Checking project structure                             | MIT License                   |
| [Sonatype OSS Index Maven Plugin][sonatype-oss-index-maven-plugin] | Checking dependencies vulnerability                    | ASL2                          |

[vs-common-jdbc]: https://github.com/exasol/virtual-schema-common-jdbc
[error-reporting-java]: https://github.com/exasol/error-reporting-java/
[hive-jdbc-driver]: https://www.cloudera.com/downloads/connectors/hive/jdbc/2-6-10.html

[apache-trift]: http://thrift.apache.org/
[exasol-jdbc-driver]: https://www.exasol.com/portal/display/DOWNLOAD/Exasol+Download+Section
[exasol-testcontainers]: https://github.com/exasol/exasol-testcontainers
[hbase-server]: http://hbase.apache.org/
[hive-jdbc-driver]: https://github.com/apache/hive/tree/master/jdbc/src/java/org/apache/hive/jdbc
[test-bd-builder]: https://github.com/exasol/test-db-builder-java

[maven-jacoco-plugin]: https://www.eclemma.org/jacoco/trunk/doc/maven.html
[maven-surefire-plugin]: https://maven.apache.org/surefire/maven-surefire-plugin/
[maven-compiler-plugin]: https://maven.apache.org/plugins/maven-compiler-plugin/
[maven-assembly-plugin]: https://maven.apache.org/plugins/maven-assembly-plugin/
[maven-failsafe-plugin]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[versions-maven-plugin]: https://www.mojohaus.org/versions-maven-plugin/
[maven-enforcer-plugin]: http://maven.apache.org/enforcer/maven-enforcer-plugin/
[artifact-ref-checker-plugin]: https://github.com/exasol/artifact-reference-checker-maven-plugin
[maven-dependency-plugin]: https://maven.apache.org/plugins/maven-dependency-plugin/
[project-keeper-maven-plugin]: https://github.com/exasol/project-keeper-maven-plugin
[sonatype-oss-index-maven-plugin]: https://sonatype.github.io/ossindex-maven/maven-plugin/

[hive-dialect-doc]: doc/user_guide/hive_user_guide.md

[vs-api]: https://github.com/exasol/virtual-schema-common-java/blob/master/doc/development/api/virtual_schema_api.md
[virtual-schemas-user-guide]: https://docs.exasol.com/database_concepts/virtual_schemas.htm
[virtual-schemas]: https://github.com/exasol/virtual-schemas
[vs-api]: https://github.com/exasol/virtual-schema-common-java/blob/master/doc/development/api/virtual_schema_api.md
[vs-doc]: https://github.com/exasol/virtual-schemas/tree/master/doc