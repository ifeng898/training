package com.example.demo.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class JPAAtomikosTransactionConfig {
   @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter=new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }
    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp=new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;

    }
    @Bean(name = "atomikosTransationManager",initMethod = "init",destroyMethod = "close")
    public TransactionManager atomikosTransationManager() throws Throwable{
        UserTransactionManager userTransactionManager=new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        AtomikosJtaPlatform.transactionManager=userTransactionManager;
        return userTransactionManager;
    }
    @Bean(name = "transactionManager")
    @DependsOn({"userTransaction","atomikosTransationManager"})
    public PlatformTransactionManager transactionManager() throws Throwable {
       UserTransaction userTransaction=userTransaction();
       AtomikosJtaPlatform.transaction=userTransaction;
       TransactionManager atomikosTransationManager=atomikosTransationManager();
       return new JtaTransactionManager(userTransaction,atomikosTransationManager);
    }
}
