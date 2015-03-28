package gov.gwssi.csc.scms.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by WangZishi on 3/27/2015.
 *
 */
@EnableWebMvc
@ComponentScan(
        basePackages = {
                "gov.gwssi.csc.scms.domain",
                "gov.gwssi.csc.scms.controller",
                "gov.gwssi.csc.scms.dao",
                "gov.gwssi.csc.scms.service"
        }
)
@Import(DatabaseConfig.class)
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}



