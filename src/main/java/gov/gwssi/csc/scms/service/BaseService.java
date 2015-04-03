package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;


/**
 * Created by WangZishi on 3/23/2015.
 */
@Service
public class BaseService {

    @Autowired
    private ApplicationContext context;
    @Autowired
    protected BaseDAO baseDAO;

    protected BaseDAO getBaseDao() {
        return baseDAO;
    }

    @SuppressWarnings("unchecked")
    protected <T extends Object> T getBean(String beanId) {
        try {
            return (T) context.getBean(beanId);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }


}
