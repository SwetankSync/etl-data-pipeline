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
public class MetadataService {

    @Inject
    SnowflakeConfig snowflakeConfig;

    public List<String> getTables(String schema) throws Exception {
        List<String> tables = new ArrayList<>();

        try (Connection conn = snowflakeConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SHOW TABLES IN SCHEMA " + schema);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tables.add(rs.getString("name"));
            }
        }

        return tables;
    }
}
