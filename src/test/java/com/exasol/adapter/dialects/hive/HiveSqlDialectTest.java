package com.exasol.adapter.dialects.hive;

import static com.exasol.adapter.AdapterProperties.CATALOG_NAME_PROPERTY;
import static com.exasol.adapter.AdapterProperties.SCHEMA_NAME_PROPERTY;
import static com.exasol.adapter.capabilities.AggregateFunctionCapability.*;
import static com.exasol.adapter.capabilities.LiteralCapability.*;
import static com.exasol.adapter.capabilities.MainCapability.*;
import static com.exasol.adapter.capabilities.PredicateCapability.*;
import static com.exasol.adapter.capabilities.ScalarFunctionCapability.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.ExaMetadata;
import com.exasol.adapter.AdapterProperties;
import com.exasol.adapter.capabilities.Capabilities;
import com.exasol.adapter.dialects.*;
import com.exasol.adapter.dialects.rewriting.SqlGenerationContext;
import com.exasol.adapter.jdbc.ConnectionFactory;
import com.exasol.adapter.jdbc.RemoteMetadataReaderException;
import com.exasol.adapter.properties.PropertyValidationException;
import com.exasol.adapter.sql.ScalarFunction;

@ExtendWith(MockitoExtension.class)
class HiveSqlDialectTest {
    private HiveSqlDialect dialect;
    private Map<String, String> rawProperties;
    @Mock
    ConnectionFactory connectionFactoryMock;
    @Mock
    ExaMetadata exaMetadataMock;

    @BeforeEach
    void beforeEach() {
        lenient().when(exaMetadataMock.getDatabaseVersion()).thenReturn("1.2.3");
        this.dialect = testee();
        this.rawProperties = new HashMap<>();
    }

    private HiveSqlDialect testee() {
        return testee(AdapterProperties.emptyProperties());
    }

    private HiveSqlDialect testee(final AdapterProperties emptyProperties) {
        return new HiveSqlDialect(
                JDBCAdapterContext.builder().properties(emptyProperties)
                        .connectionFactory(connectionFactoryMock)
                        .metadata(exaMetadataMock).build());
    }

    @Test
    void testGetCapabilities() {
        final Capabilities capabilities = this.dialect.getCapabilities();
        assertAll(
                () -> assertThat(capabilities.getMainCapabilities(),
                        containsInAnyOrder(SELECTLIST_PROJECTION, SELECTLIST_EXPRESSIONS, FILTER_EXPRESSIONS,
                                AGGREGATE_SINGLE_GROUP, AGGREGATE_GROUP_BY_COLUMN, AGGREGATE_HAVING, ORDER_BY_COLUMN,
                                ORDER_BY_EXPRESSION, LIMIT, JOIN, JOIN_TYPE_INNER, JOIN_TYPE_LEFT_OUTER,
                                JOIN_TYPE_RIGHT_OUTER, JOIN_TYPE_FULL_OUTER, JOIN_CONDITION_EQUI)), //
                () -> assertThat(capabilities.getLiteralCapabilities(),
                        containsInAnyOrder(NULL, BOOL, DATE, TIMESTAMP, DOUBLE, EXACTNUMERIC, STRING)),
                () -> assertThat(capabilities.getPredicateCapabilities(),
                        containsInAnyOrder(AND, OR, NOT, EQUAL, NOTEQUAL, LESS, LESSEQUAL, LIKE, REGEXP_LIKE, BETWEEN,
                                IN_CONSTLIST, IS_NULL, IS_NOT_NULL)),
                () -> assertThat(capabilities.getAggregateFunctionCapabilities(),
                        containsInAnyOrder(COUNT, COUNT_STAR, COUNT_DISTINCT, SUM, SUM_DISTINCT, MIN, MAX, AVG,
                                AVG_DISTINCT, STDDEV_POP, STDDEV_POP_DISTINCT, STDDEV_SAMP, STDDEV_SAMP_DISTINCT,
                                VAR_POP, VAR_POP_DISTINCT, VAR_SAMP, VAR_SAMP_DISTINCT, COUNT_TUPLE)),
                () -> assertThat(capabilities.getScalarFunctionCapabilities(),
                        containsInAnyOrder(ADD, SUB, MULT, FLOAT_DIV, NEG, ABS, ACOS, ASIN, ATAN, CEIL, COS, DEGREES,
                                DIV, EXP, FLOOR, LN, LOG, MOD, POWER, RADIANS, SIGN, SIN, SQRT, TAN, ASCII, CONCAT,
                                LENGTH, LOWER, LPAD, REPEAT, REVERSE, RPAD, SOUNDEX, SPACE, SUBSTR, TRANSLATE, UPPER,
                                ADD_DAYS, ADD_MONTHS, CURRENT_DATE, CURRENT_TIMESTAMP, DATE_TRUNC, DAY, DAYS_BETWEEN,
                                MINUTE, MONTH, MONTHS_BETWEEN, SECOND, WEEK, CAST, BIT_AND, BIT_OR, BIT_XOR,
                                CURRENT_USER, BIT_LSHIFT, BIT_RSHIFT, HOUR, INITCAP)));
    }

    @Test
    void testGetName() {
        assertThat(this.dialect.getName(), equalTo("HIVE"));
    }

    @Test
    void testValidateCatalogProperty() throws PropertyValidationException {
        setMandatoryProperties();
        this.rawProperties.put(CATALOG_NAME_PROPERTY, "MY_CATALOG");
        final AdapterProperties adapterProperties = new AdapterProperties(this.rawProperties);
        final SqlDialect sqlDialect = testee(adapterProperties);
        sqlDialect.validateProperties();
    }

    @Test
    void testValidateSchemaProperty() throws PropertyValidationException {
        setMandatoryProperties();
        this.rawProperties.put(SCHEMA_NAME_PROPERTY, "MY_SCHEMA");
        final AdapterProperties adapterProperties = new AdapterProperties(this.rawProperties);
        final SqlDialect sqlDialect = testee(adapterProperties);
        sqlDialect.validateProperties();
    }

    @Test
    void testSupportsJdbcCatalogs() {
        assertThat(this.dialect.supportsJdbcCatalogs(), equalTo(SqlDialect.StructureElementSupport.SINGLE));
    }

    @Test
    void testSupportsJdbcSchemas() {
        assertThat(this.dialect.supportsJdbcSchemas(), equalTo(SqlDialect.StructureElementSupport.MULTIPLE));
    }

    @CsvSource({ "tableName, `tableName`", //
            "`tableName, ```tableName`" //
    })
    @ParameterizedTest
    void testApplyQuote(final String unquoted, final String quoted) {
        assertThat(this.dialect.applyQuote(unquoted), equalTo(quoted));
    }

    @ValueSource(strings = { "ab:'ab'", "a'b:'a\\'b'", "a''b:'a\\'\\'b'", "'ab':'\\'ab\\''", "a\\b:'a\\\\b'",
            "a\\\\b:'a\\\\\\\\b'", "a\\'b:'a\\\\\\'b'" })
    @ParameterizedTest
    void testGetLiteralString(final String definition) {
        assertThat(this.dialect.getStringLiteral(definition.substring(0, definition.indexOf(':'))),
                equalTo(definition.substring(definition.indexOf(':') + 1)));
    }

    @Test
    void testGetLiteralStringNull() {
        assertThat(this.dialect.getStringLiteral(null), CoreMatchers.equalTo("NULL"));
    }

    @Test
    void testRequiresCatalogQualifiedTableNames() {
        assertThat(this.dialect.requiresCatalogQualifiedTableNames(null), equalTo(false));
    }

    @Test
    void testRequiresSchemaQualifiedTableNames() {
        assertThat(this.dialect.requiresSchemaQualifiedTableNames(null), equalTo(true));
    }

    @Test
    void testGetDefaultNullSorting() {
        assertThat(this.dialect.getDefaultNullSorting(), equalTo(SqlDialect.NullSorting.NULLS_SORTED_LOW));
    }

    @Test
    void testGetSqlGenerator() {
        final SqlGenerationContext context = new SqlGenerationContext("catalog", "schema", false);
        assertThat(this.dialect.getSqlGenerator(context), instanceOf(HiveSqlGenerationVisitor.class));
    }

    @Test
    void testGetScalarFunctionAliases() {
        assertThat(this.dialect.getScalarFunctionAliases(),
                equalTo(Map.of(ScalarFunction.ADD_DAYS, "DATE_ADD", ScalarFunction.DAYS_BETWEEN, "DATEDIFF",
                        ScalarFunction.WEEK, "WEEKOFYEAR", ScalarFunction.CURRENT_USER, "CURRENT_USER()",
                        ScalarFunction.BIT_LSHIFT, "SHIFTLEFT", ScalarFunction.BIT_RSHIFT, "SHIFTRIGHT")));
    }

    @Test
    void testCreateRemoteMetadataReader() {
        assertThat(testee().createRemoteMetadataReader(),
                instanceOf(HiveMetadataReader.class));
    }

    @Test
    void testCreateRemoteMetadataReaderWrapsSqlException() throws SQLException {
        when(connectionFactoryMock.getConnection()).thenThrow(new SQLException("connection failed"));
        final RemoteMetadataReaderException exception = assertThrows(RemoteMetadataReaderException.class,
                dialect::createRemoteMetadataReader);

        assertAll(() -> assertThat(exception.getMessage(), containsString("E-VSHIVE-1")),
                () -> assertThat(exception.getMessage(), containsString("connection failed")));
    }

    @Test
    void testCreateQueryRewriter() {
        assertThat(testee().createQueryRewriter(),
                instanceOf(QueryRewriter.class));
    }

    private void setMandatoryProperties() {
        this.rawProperties.put(AdapterProperties.CONNECTION_NAME_PROPERTY, "MY_CONN");
    }
}
