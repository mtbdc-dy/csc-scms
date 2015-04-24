package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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

    protected <T> T setNullByField(T bean, String fieldName, Class<T> clazz) {
        if (bean == null) {
            return null;
        }
        Object[] obj = new Object[]{null};
        String methodName = "set" + fieldName.substring(0, 1)
                .toUpperCase() + fieldName.substring(1);
        try {
            System.out.println("seter " + fieldName + "::" + methodName);
            Method setMethod = clazz.getMethod(methodName);
            setMethod.invoke(bean, obj);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            return bean;
        }
    }

    protected <T> List<T> setNullByField(List<T> beans, String fieldName, Class<T> clazz) {
        if (beans == null || beans.size() == 0)
            return beans;
        Object[] obj = new Object[]{null};
        String methodName = "set" + fieldName.substring(0, 1)
                .toUpperCase() + fieldName.substring(1);
        try {
            Method setMethod = clazz.getMethod(methodName);
            for (T bean : beans)
                setMethod.invoke(bean, obj);
            return beans;
        } catch (Exception e) {
            e.printStackTrace();
            return beans;
        }
    }
}
