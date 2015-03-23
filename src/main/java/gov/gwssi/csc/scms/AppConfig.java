package gov.gwssi.csc.scms;

import gov.gwssi.csc.scms.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by WangZishi on 3/23/2015.
 */
@Configuration
public class AppConfig {

    @Bean(name = "userBean")
    @Scope("prototype")
    public User userFactory(){
        User user = new User();
        return user;
    }

}
