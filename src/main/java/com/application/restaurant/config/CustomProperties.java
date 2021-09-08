package com.application.restaurant.config;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CustomProperties extends Properties {

    private static final long serialVersionUID = 1L;

    public CustomProperties(DataSource dataSource) {
        super();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> configs = jdbcTemplate
                .queryForList("select config_key, config_value from system_parameters");

        for (Map<String, Object> config : configs) {
            setProperty((config.get("config_key")).toString(), (config.get("config_value")).toString());
        }
    }
}
