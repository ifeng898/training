package com.example.demo.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Primary
    @Bean("primarilyDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.primarily")
    public DataSource primarlyDatasource(){
        return DataSourceBuilder.create().build();
    }


    @Bean("secondlyDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.secondly")
    public DataSource secondlyDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("secondlylyJdbcTemplate")
    public JdbcTemplate secondlylyJdbcTemplate(@Qualifier("secondlyDatasource") DataSource datasource){
        return new JdbcTemplate(datasource);
    }
    @Bean("primarilyJdbcTemplate")
    public JdbcTemplate primarilyJdbcTemplate(@Qualifier("primarilyDatasource") DataSource datasource){
        return new JdbcTemplate(datasource);
    }
}
