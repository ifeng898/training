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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(entityManagerFactoryRef = "primaryEntityManager",
        transactionManagerRef = "transactionManager",
        basePackages = "com.example.demo.dao.testdb")
public class JpaPrimarilyConfig {
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;
    @Resource
    private JpaProperties jpaProperties;
    @Primary
    @Bean(name = "primaryDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.primarily")
   public DataSourceProperties primaryDataSourceProperties(){
       return new DataSourceProperties();
   }
    @Primary
    @Bean(name = "primaryDataSource",initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.primarily")
    public DataSource primarlyDatasource() throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(primaryDataSourceProperties().getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(primaryDataSourceProperties().getPassword());
        mysqlXADataSource.setUser(primaryDataSourceProperties().getUsername());
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("primary");
        return xaDataSource;
    }



    @Primary
    @Bean(name = "primaryEntityManager")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean primaryEntityManager() throws Throwable {
        HashMap<String,Object> properties = new HashMap<>();
        properties.put("hibernate.transaction.jta.platform",AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType","JTA");
        LocalContainerEntityManagerFactoryBean entityManager =new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(primarlyDatasource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.example.demo.dao.testdb");
        entityManager.setPersistenceUnitName("primaryPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }


}
