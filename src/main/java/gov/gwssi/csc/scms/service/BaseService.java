package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.dao.BaseDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by WangZishi on 3/23/2015.
 */
@Service
public class BaseService {

    @Autowired
    private ApplicationContext context;
    @Autowired
    @Qualifier("baseDao")
    protected BaseDao baseDao;

//    public BaseService(){
//        baseDao = (BaseDao)getBean("baseDao");
//        System.out.println("BaseService");
//    }

    @SuppressWarnings("unchecked")
    protected <T extends Object> T getBean(String beanId) {
        try {
            return (T)context.getBean(beanId);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }
}
