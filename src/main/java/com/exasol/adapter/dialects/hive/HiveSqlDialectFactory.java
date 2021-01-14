package com.exasol.adapter.dialects.hive;

import com.exasol.adapter.AdapterProperties;
import com.exasol.adapter.dialects.*;
import com.exasol.adapter.jdbc.ConnectionFactory;
import com.exasol.logging.VersionCollector;

/**
 * Factory for the Hive SQL dialect.
 */
public class HiveSqlDialectFactory implements SqlDialectFactory {
    @Override
    public String getSqlDialectName() {
        return HiveSqlDialect.NAME;
    }

    @Override
    public SqlDialect createSqlDialect(final ConnectionFactory connectionFactory, final AdapterProperties properties) {
        return new HiveSqlDialect(connectionFactory, properties);
    }

    @Override
    public String getSqlDialectVersion() {
        final VersionCollector versionCollector = new VersionCollector(
                "META-INF/maven/com.exasol/hive-virtual-schema/pom.properties");
        return versionCollector.getVersionNumber();
    }
}