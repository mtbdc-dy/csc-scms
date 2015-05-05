package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;


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

    protected <T extends Object> T getBean(String beanId, Class<T> clazz) {
        try {
            return context.getBean(beanId, clazz);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <T> T setNullByField(T bean, String fieldName, Class<T> clazz) {
        if (bean == null) {
            return null;
        }
        try {
            Object[] obj = new Object[]{null};
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Method writeMethod = pd.getWriteMethod();
            writeMethod.invoke(bean, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    protected <T> List<T> setNullByField(List<T> beans, String fieldName, Class<T> clazz) {
        if (beans == null || beans.size() == 0)
            return beans;
        try {
            Object[] obj = new Object[]{null};
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Method setMethod = pd.getWriteMethod();
            for (T bean : beans)
                setMethod.invoke(bean, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beans;
    }
}
