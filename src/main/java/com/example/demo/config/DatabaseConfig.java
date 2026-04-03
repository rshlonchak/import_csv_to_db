package com.example.demo.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfig {

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .locations("classpath:db/migration")
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .load();
    }

    @Bean
    InitializingBean flywayMigrate(Flyway flyway) {
        return flyway::migrate;
    }
}
