package com.exasol.adapter.dialects.hive;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.exasol.adapter.dialects.JDBCAdapterContext;

class HiveSqlDialectFactoryTest {
    private HiveSqlDialectFactory factory;

    @BeforeEach
    void beforeEach() {
        this.factory = new HiveSqlDialectFactory();
    }

    @Test
    void testGetName() {
        assertThat(this.factory.getSqlDialectName(), equalTo("HIVE"));
    }

    @Test
    void testCreateDialect() {
        assertThat(this.factory.createSqlDialect(JDBCAdapterContext.builder().build()),
                instanceOf(HiveSqlDialect.class));
    }

    @Test
    void testGetSqlDialectVersion() {
        // Only works when running from a built artifact
        assertThat(this.factory.getSqlDialectVersion(), equalTo("UNKNOWN"));
    }

    @Test
    void testGetAdapterProjectShortTag() {
        assertThat(this.factory.getAdapterProjectShortTag(), equalTo("VSHIVE"));
    }
}
