package gov.gwssi.csc.scms;

import gov.gwssi.csc.scms.configuration.DatabaseConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by WangZishi on 4/1/2015.
 * <p/>
 * 配置测试Context
 */
//@EnableWebMvc
@ComponentScan(
        basePackages = {
                "gov.gwssi.csc.scms.service.student",
                "gov.gwssi.csc.scms.dao",
                "gov.gwssi.csc.scms.domain.student",
                "gov.gwssi.csc.scms.repository.student",
                "gov.gwssi.csc.scms.service.dictionary",
                "gov.gwssi.csc.scms.domain.dictionary",
                "gov.gwssi.csc.scms.repository.dictionary"
        }
)
@Import(DatabaseConfig.class)
@Configuration
public class TestConfig {
}
