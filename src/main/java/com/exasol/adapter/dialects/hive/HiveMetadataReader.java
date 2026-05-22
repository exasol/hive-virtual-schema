package com.exasol.adapter.dialects.hive;

import java.sql.Connection;

import com.exasol.ExaMetadata;
import com.exasol.adapter.AdapterProperties;
import com.exasol.adapter.dialects.*;
import com.exasol.adapter.jdbc.*;

/**
 * This class reads Hive-specific database metadata.
 */
public class HiveMetadataReader extends AbstractRemoteMetadataReader {
    /**
     * Create a new instance of the {@link HiveMetadataReader}
     *
     * @param connection  database connection through which the reader retrieves the metadata from the remote source
     * @param properties  user-defined properties
     * @param exaMetadata Exasol metadata
     */
    public HiveMetadataReader(final Connection connection, final AdapterProperties properties, final ExaMetadata exaMetadata) {
        super(connection, properties, exaMetadata);
    }

    @Override
    protected IdentifierConverter createIdentifierConverter() {
        return new BaseIdentifierConverter(IdentifierCaseHandling.INTERPRET_AS_LOWER,
                IdentifierCaseHandling.INTERPRET_AS_LOWER);
    }

    @Override
    protected ColumnMetadataReader createColumnMetadataReader() {
        return new HiveColumnMetadataReader(this.connection, this.properties, this.exaMetadata, this.identifierConverter);
    }

    @Override
    protected TableMetadataReader createTableMetadataReader() {
        return new BaseTableMetadataReader(this.connection, this.columnMetadataReader, this.properties, this.exaMetadata,
                this.identifierConverter);
    }
}
