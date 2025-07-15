package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.config.SnowflakeConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SnowflakeMetadataService {

    @Inject
    SnowflakeConfig snowflakeConfig;

    public List<String> getTables(String schema) throws Exception {
        List<String> tables = new ArrayList<>();

        // Validate schema format: must be like DATABASE.SCHEMA
        if (schema == null || !schema.contains(".")) {
            throw new IllegalArgumentException("Schema must be in format DATABASE.SCHEMA");
        }

        String sql = "SHOW TABLES IN SCHEMA " + schema;

        try (Connection conn = snowflakeConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tables.add(rs.getString("name"));
            }
        }

        return tables;
    }
}
