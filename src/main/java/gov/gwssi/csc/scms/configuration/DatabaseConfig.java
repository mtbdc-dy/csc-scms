package gov.gwssi.csc.scms.configuration;


import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Map;
import java.util.Properties;

@PropertySource(value = "classpath:db.properties")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("gov.gwssi.csc.scms.repository")
@EnableAspectJAutoProxy
@Configuration
public class DatabaseConfig {

    @Autowired
    Environment env;

    @Bean
    public BoneCPDataSource boneCPDataSource() {

        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        // 获取系统环境变量
        Map map = System.getenv();
        if (map.containsKey("JDBC_URL") && map.containsKey("JDBC_USERNAME") && map.containsKey("JDBC_PASSWORD")) {
            String jdbcUrl = map.get("JDBC_URL").toString();
            String jdbcUsername = map.get("JDBC_USERNAME").toString();
            String jdbcPassword = map.get("JDBC_PASSWORD").toString();
            boneCPDataSource.setJdbcUrl(jdbcUrl);
            boneCPDataSource.setUsername(jdbcUsername);
            boneCPDataSource.setPassword(jdbcPassword);
        }else{
            boneCPDataSource.setJdbcUrl(env.getProperty("jdbc.test.url"));
            boneCPDataSource.setUsername(env.getProperty("jdbc.test.username"));
            boneCPDataSource.setPassword(env.getProperty("jdbc.test.password"));
        }
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
        boneCPDataSource.setIdleMaxAgeInMinutes(420);
        boneCPDataSource.setMaxConnectionsPerPartition(100);
        boneCPDataSource.setMinConnectionsPerPartition(10);
        boneCPDataSource.setPartitionCount(3);
        boneCPDataSource.setAcquireIncrement(5);
        boneCPDataSource.setStatementsCacheSize(100);
        // boneCPDataSource.setReleaseHelperThreads(3);


        return boneCPDataSource;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }


    @Bean(name = "entityManagerFactory")
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(BoneCPDataSource dataSource) {

        System.out.println("dataSource===" + dataSource);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setDatabasePlatform(env.getProperty("hibernate.dialect"));
        vendorAdapter.setDatabase(Database.ORACLE);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(env.getProperty("domain.packages"));
        //factory.setPackagesToScan("gov.gwssi.csc.scms.domain.student","gov.gwssi.csc.scms.domain.dictionary","gov.gwssi.csc.scms.domain.abnormal");
        factory.setDataSource(dataSource);

        Properties properties = new Properties();
        // properties.setProperty("hibernate.cache.use_second_level_cache", "true");
        // properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        // properties.setProperty("hibernate.cache.use_query_cache", "true");
        // properties.setProperty("hibernate.generate_statistics", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        factory.setJpaProperties(properties);
        // factory.afterPropertiesSet();

        return factory;
    }

    @Bean(name = "transactionManager")
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        JpaDialect jpaDialect = new HibernateJpaDialect();
        txManager.setEntityManagerFactory(entityManagerFactory);
        txManager.setJpaDialect(jpaDialect);
        return txManager;
    }

}
