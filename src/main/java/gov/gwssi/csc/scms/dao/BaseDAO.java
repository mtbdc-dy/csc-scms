package gov.gwssi.csc.scms.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.*;

/**
 * Created by Wang Rui on 2015/3/30.
 */
public class BaseDAO {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    /**
     * 查询的结果是对象数组的集合
     */
    public List queryListBySql(String sql) {
        EntityManager em = entityManagerFactory.createEntityManager();
        //创建原生SQL查询QUERY实例
        Query query =  em.createNativeQuery(sql);
        //执行查询，返回的是对象数组(Object[])列表,
        //每一个对象数组存的是相应的实体属性
        List objecArraytList = query.getResultList();
        for(int i=0;i<objecArraytList.size();i++) {
            Object[] obj = (Object[]) objecArraytList.get(i);
            //使用obj[0],obj[1],obj[2]...取出属性　　　　
        }
        em.close();
        return objecArraytList;
    }

}
