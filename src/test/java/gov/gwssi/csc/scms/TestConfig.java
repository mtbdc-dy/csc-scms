package gov.gwssi.csc.scms;

import gov.gwssi.csc.scms.configuration.DatabaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by WangZishi on 4/1/2015.
 *
 * 配置测试Context
 */
@EnableWebMvc
@ComponentScan(
    basePackages = {
        "gov.gwssi.csc.scms.service",
        "gov.gwssi.csc.scms.dao"
    }
)
@Import(DatabaseConfig.class)
@Configuration
public class TestConfig {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String driver;

    @Bean(name = "scmsDS")
    public DriverManagerDataSource getMyDriverManager(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }
}
