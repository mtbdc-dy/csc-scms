package gov.gwssi.csc.scms.base;

import gov.gwssi.csc.scms.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation
        .AnnotationConfigApplicationContext;

import org.springframework.test.context.ContextConfiguration;



/**
 * Created by WangZishi on 4/1/2015.
 *
 */
@ContextConfiguration(classes = TestConfig.class)
public class UnitTestBase{

    private AnnotationConfigApplicationContext context;

    @Before
    public void before() {
        try {
            context = new AnnotationConfigApplicationContext(TestConfig.class);
            context.start();
        } catch (BeansException e) {
            e.printStackTrace();
        }
        System.out.println("context = " + context);
    }

    @After
    public void after() {
        context.destroy();
    }

    @SuppressWarnings("unchecked")
    protected <T extends Object> T getBean(String beanId) {
        try {
            return (T)context.getBean(beanId);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <T extends Object> T getBean(Class<T> clazz) {
        try {
            return context.getBean(clazz);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }

}
