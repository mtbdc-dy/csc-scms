package gov.gwssi.csc.scms;

import gov.gwssi.csc.scms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by WangZishi on 3/23/2015.
 */
@Configuration
public class AppConfig {

    @Autowired(required = false)
    @Qualifier("scmsDS")
    private DataSource dataSource;

    @Bean(name = "userBean")
    @Scope("prototype")
    public User userFactory(){
        User user = new User();
        return user;
    }

    @Bean(name = "jdbcTemplate")
    @Scope("singleton")
    public JdbcTemplate getJbdcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}
