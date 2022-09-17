package com.example.demo.config;



import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
;

import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(entityManagerFactoryRef = "secondlyEntityManager",
        transactionManagerRef = "transactionManager",
        basePackages = "com.example.demo.dao.testdb2")
public class JpaSecondlyConfig {
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;
    @Resource
    private JpaProperties jpaProperties;

    @Bean(name = "secondlyDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.secondly")
    public DataSourceProperties secondlyDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "secondlyDataSource",initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.secondly")
    public DataSource secondlyDatasource() throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(secondlyDataSourceProperties().getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(secondlyDataSourceProperties().getPassword());
        mysqlXADataSource.setUser(secondlyDataSourceProperties().getUsername());
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("secondly");
        return xaDataSource;
    }




    @Bean(name = "secondlyEntityManager")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean secondlyEntityManager() throws Throwable {
        HashMap<String,Object> properties = new HashMap<>();
        properties.put("hibernate.transaction.jta.platform",AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType","JTA");
        LocalContainerEntityManagerFactoryBean entityManager =new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(secondlyDatasource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.example.demo.dao.testdb2");
        entityManager.setPersistenceUnitName("secondlyPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }


}
