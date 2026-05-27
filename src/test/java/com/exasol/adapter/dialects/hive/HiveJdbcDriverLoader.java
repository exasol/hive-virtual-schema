package com.exasol.adapter.dialects.hive;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.sql.Driver;

class HiveJdbcDriverLoader implements AutoCloseable {
    private static final File HIVE_JDBC_DRIVER = new File("src/test/resources/integration/driver/hive/HiveJDBC42.jar");
    private static final String DRIVER_CLASS_NAME = "com.cloudera.hive.jdbc.HS2Driver";

    private final URLClassLoader classLoader;

    HiveJdbcDriverLoader() {
        this.classLoader = createClassLoader();
    }

    private static URLClassLoader createClassLoader() {
        try {
            final URL driverUrl = HIVE_JDBC_DRIVER.toURI().toURL();
            return new URLClassLoader(new URL[] { driverUrl }, HiveJdbcDriverLoader.class.getClassLoader());
        } catch (final MalformedURLException exception) {
            throw new IllegalStateException("Could not create Hive JDBC driver class loader", exception);
        }
    }

    Driver load() {
        try {
            final Class<?> driverClass = this.classLoader.loadClass(DRIVER_CLASS_NAME);
            return (Driver) driverClass.getDeclaredConstructor().newInstance();
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            throw new IllegalStateException("Could not load Hive JDBC driver", exception);
        }
    }

    @Override
    public void close() {
        try {
            this.classLoader.close();
        } catch (final IOException exception) {
            throw new UncheckedIOException("Could not close Hive JDBC driver class loader", exception);
        }
    }
}
