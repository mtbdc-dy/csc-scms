package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


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

    protected <T> void copyFiledValue(Class<T> clazz, T source, T target) {

        if (target == null) {
            target = source;
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object oValue = getMethod.invoke(source);
                if (oValue == null) {
                    continue;
                } else {
                    pd.getWriteMethod().invoke(target, oValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

}
