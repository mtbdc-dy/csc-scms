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
    /**
     * 根据字段名称取值
     * @param obj
     * @param fieldName
     * @return
     */
    public static  Object getClassValue(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        }
        try {
            Class beanClass = obj.getClass();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                // 非get方法不取
                if (!ms[i].getName().startsWith("get")) {
                    continue;
                }
                Object objValue = null;
                try {
                    objValue = ms[i].invoke(obj, new Object[] {});
                } catch (Exception e) {

                    continue;
                }
                if (objValue == null) {
                    continue;
                }
                if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
                        || ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())) {
                    return objValue;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }


     public static boolean classOfSrc(Object source, Object target, boolean rv) {
        Class<?> srcClass = source.getClass();
        Field[] fields = srcClass.getDeclaredFields();
        for (Field field : fields) {
            String nameKey = field.getName();

                String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey)
                        .toString();
                String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey)
                        .toString();
                if (!srcValue.equals(tarValue)) {
                    rv = false;
                    break;
                }

        }
        return rv;
    }


}
