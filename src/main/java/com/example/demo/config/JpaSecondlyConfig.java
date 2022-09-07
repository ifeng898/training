package com.example.demo.config;


import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "EntityManagerFactorySecondly", transactionManagerRef = "transactionManagerSecondly", basePackages = "com.example.demo.dao.testdb2")
public class JpaSecondlyConfig {


    @Resource
    private JpaProperties jpaProperties;
    @Resource
    private HibernateProperties hibernateProperties;

    @Bean("SecondlyDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.secondly")
    public DataSource SecondlyDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerSecondly")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return EntityManagerFactorySecondly(builder).getObject().createEntityManager();
    }

    @Bean(name = "EntityManagerFactorySecondly")
    public LocalContainerEntityManagerFactoryBean EntityManagerFactorySecondly(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        return builder.dataSource(SecondlyDatasource()).properties(properties).packages("com.example.demo.dao.testdb2").persistenceUnit("SecondlyPersistenceUnit").build();
    }

    @Bean(name = "transactionManagerSecondly")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder){
        return  new JpaTransactionManager(EntityManagerFactorySecondly(builder).getObject());
    }

}
