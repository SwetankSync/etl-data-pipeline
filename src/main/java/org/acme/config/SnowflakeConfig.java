package org.acme.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@ApplicationScoped
public class SnowflakeConfig {

    @ConfigProperty(name = "snowflake.url")
    String url;

    @ConfigProperty(name = "snowflake.user")
    String user;

    @ConfigProperty(name = "snowflake.password")
    String password;

    @ConfigProperty(name = "snowflake.role")
    String role;

    @ConfigProperty(name = "snowflake.warehouse")
    String warehouse;

    @ConfigProperty(name = "snowflake.database")
    String database;

    public Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.put("user", user);
        props.put("password", password);
        props.put("role", role);
        props.put("warehouse", warehouse);
        props.put("db", database);

        return DriverManager.getConnection(url, props);
    }
}
