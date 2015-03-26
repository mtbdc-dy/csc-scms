package gov.gwssi.csc.scms.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * User: ryan
 * Date: 2/7/13
 */
@EnableWebMvc
@ComponentScan(basePackages = {"gov.gwssi.csc.scms.model", "gov.gwssi.csc.scms.controller", "gov.gwssi.csc.scms.service"})
@Import(DatabaseConfig.class)
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}



