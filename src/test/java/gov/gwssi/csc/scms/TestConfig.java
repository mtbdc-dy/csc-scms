package gov.gwssi.csc.scms;

import gov.gwssi.csc.scms.configuration.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
<<<<<<< HEAD
 * Created by WangZishi on 4/1/2015.
 * <p/>
 * 配置测试Context
=======
 * Created by WangZishi on 3/23/2015.
 *
>>>>>>> remotes/origin/native_Hibernate
 */
//@EnableWebMvc
@ComponentScan(
        basePackages = {
                "gov.gwssi.csc.scms.service.student",
                "gov.gwssi.csc.scms.service.abnormal",
                "gov.gwssi.csc.scms.service.ticket",
                "gov.gwssi.csc.scms.dao",
                "gov.gwssi.csc.scms.domain.student",
                "gov.gwssi.csc.scms.domain.log",
                "gov.gwssi.csc.scms.domain.user",
                "gov.gwssi.csc.scms.repository.student",
                "gov.gwssi.csc.scms.repository.log",
                "gov.gwssi.csc.scms.service.log",
                "gov.gwssi.csc.scms.service.user",
                "gov.gwssi.csc.scms.service.dictionary",
                "gov.gwssi.csc.scms.domain.dictionary",
                "gov.gwssi.csc.scms.service.statistics",
                "gov.gwssi.csc.scms.repository.statistics",
                "gov.gwssi.csc.scms.service.codemaintenance",
                "gov.gwssi.csc.scms.domain.statistics"
        }
)
@Import(DatabaseConfig.class)
@Configuration
public class TestConfig {
}
