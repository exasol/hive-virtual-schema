package com.exasol.adapter.dialects.hive;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.ExaMetadata;
import com.exasol.adapter.AdapterProperties;
import com.exasol.adapter.dialects.BaseIdentifierConverter;
import com.exasol.adapter.jdbc.ColumnMetadataReader;
import com.exasol.adapter.jdbc.JDBCTypeDescription;
import com.exasol.adapter.metadata.DataType;

@ExtendWith(MockitoExtension.class)
class HiveColumnMetadataReaderTest {
    private HiveColumnMetadataReader columnMetadataReader;
    @Mock
    ExaMetadata exaMetadataMock;

    @BeforeEach
    void beforeEach() {
        this.columnMetadataReader = testee(AdapterProperties.emptyProperties());
    }

    @Test
    void mapDecimalReturnDecimal() {
        final JDBCTypeDescription typeDescription = new JDBCTypeDescription(Types.DECIMAL, 0,
                DataType.MAX_EXASOL_DECIMAL_PRECISION, 10, "DECIMAL");
        assertThat(this.columnMetadataReader.mapJdbcType(typeDescription), equalTo(DataType.createDecimal(36, 0)));
    }

    @Test
    void mapDecimalReturnVarchar() {
        final JDBCTypeDescription typeDescription = new JDBCTypeDescription(Types.DECIMAL, 0,
                DataType.MAX_EXASOL_DECIMAL_PRECISION + 1, 10, "DECIMAL");
        assertThat(this.columnMetadataReader.mapJdbcType(typeDescription),
                equalTo(DataType.createMaximumSizeVarChar(DataType.ExaCharset.UTF8)));
    }

    @Test
    void mapBinaryReturnVarchar() {
        final JDBCTypeDescription typeDescription = new JDBCTypeDescription(Types.BINARY, 0, 0, 0, "BINARY");
        assertThat(this.columnMetadataReader.mapJdbcType(typeDescription),
                equalTo(DataType.createMaximumSizeVarChar(DataType.ExaCharset.UTF8)));
    }

    @Test
    void testMapColumnTypeBeyondMaxExasolDecimalPrecisionWithCastProperty() {
        final Map<String, String> rawProperties = new HashMap<>();
        rawProperties.put(HiveProperties.HIVE_CAST_NUMBER_TO_DECIMAL_PROPERTY, "10,2");
        final AdapterProperties properties = new AdapterProperties(rawProperties);
        final ColumnMetadataReader reader = testee(properties);
        final JDBCTypeDescription typeDescription = new JDBCTypeDescription(Types.DECIMAL, 0,
                DataType.MAX_EXASOL_DECIMAL_PRECISION + 1, 10, "DECIMAL");
        assertThat(reader.mapJdbcType(typeDescription), equalTo(DataType.createDecimal(10, 2)));
    }

    private HiveColumnMetadataReader testee(final AdapterProperties properties) {
        when(this.exaMetadataMock.getDatabaseVersion()).thenReturn("2025.1.2");
        return new HiveColumnMetadataReader(null, properties, exaMetadataMock,
                BaseIdentifierConverter.createDefault());
    }
}
