package gov.gwssi.csc.scms.dao;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by Wang Rui on 2015/3/30.
 */
@Service
public class BaseDAO {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    DataSource dataSource;

    /**
     * 查询的结果是List<Map>
     */
    public List queryListBySql(String sql) {
        List<Map> objectList;
        EntityManager em = null;

        try {
            em = entityManagerFactory.createEntityManager();
            //创建原生SQL查询QUERY实例
            Query query = em.createNativeQuery(sql);
            //list转为List<Map>
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            objectList = query.getResultList();
            return objectList;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public <T> List<T> getObjectListByHQL(String hSql, Class<T> clazz) {
        return getObjectListByHQL(hSql, clazz, 0, 200);
    }

    public <T> List<T> getObjectListByHQL(String hSql, Class<T> clazz, int startPosition, int pageSize) {
        EntityManager em = null;
        List<T> list;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery(hSql);
            query.setFirstResult(startPosition);
            query.setMaxResults(pageSize);
            list = query.getResultList();
            return list;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public int getCountObjectByHQL(String hSql) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery(hSql);
            Object o = query.getSingleResult();
            return Integer.parseInt(o.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
    /**
     * add by lzs 20150603
     * 调用存储过程
     * name 存储名
     * list 参数列表 可以是单个，或者多个
     */

    public void doStatement(String name, List list) {
        JdbcTemplate jdbcTemplate =getJdbcTemplate();
        String callName = "{ call " + name + " (\'"; // call存储过程名
        for (int i = 0; i < list.size()-1; i++) { // 取参数的问号
            callName = callName + list.get(i)+"\',\' ";
//            System.out.println(callName);
        }
        callName = callName +list.get(list.size()-1)+ "\') }";

        jdbcTemplate.execute(callName);

    }
    public String getIdBySequence(String sequenceName) {
        String sql = "select f_scms_gen_id('" + sequenceName + "') from dual ";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            return String.valueOf(query.getSingleResult());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public String getDicIdByClassType(String classType){
        String sql = "select f_scms_dim_id('" + classType + "') from dual ";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            return String.valueOf(query.getSingleResult());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * 执行sql修改
     */
    public int updateBySql(String sql) {
        List<Map> objectList;
        EntityManager em = null;

        try {
            em = entityManagerFactory.createEntityManager();
            em.joinTransaction();
            //创建原生SQL查询QUERY实例
            Query query = em.createNativeQuery(sql);
            int num = query.executeUpdate();
            return num;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


}