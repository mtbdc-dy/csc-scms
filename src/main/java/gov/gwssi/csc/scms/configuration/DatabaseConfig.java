package gov.gwssi.csc.scms.configuration;


import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;


import java.util.Properties;

/**
 * Created by WangZishi on 3/27/2015.
 *
 * 与数据库相关的配置
 * 包含了数据源Database, JdbcTemplate, SessionFactory, TransactionManager等。
 */
@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:db.properties"})
@ComponentScan(basePackages = {"gov.gwssi.csc.scms.repository"})
public class DatabaseConfig {

    @Autowired
    Environment env;

    @Bean
    public BoneCPDataSource boneCPDataSource() {

        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        boneCPDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        boneCPDataSource.setUsername(env.getProperty("jdbc.username"));
        boneCPDataSource.setPassword(env.getProperty("jdbc.password"));
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
        boneCPDataSource.setIdleMaxAgeInMinutes(420);
        boneCPDataSource.setMaxConnectionsPerPartition(30);
        boneCPDataSource.setMinConnectionsPerPartition(10);
        boneCPDataSource.setPartitionCount(3);
        boneCPDataSource.setAcquireIncrement(5);
        boneCPDataSource.setStatementsCacheSize(100);
        boneCPDataSource.setReleaseHelperThreads(3);

        return boneCPDataSource;

    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(boneCPDataSource());
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(boneCPDataSource());
        sessionFactory.setPackagesToScan(new String[]{"gov.gwssi.csc.scms.domain"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }

    @Bean
    public HibernateTransactionManager transactionManager()
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
