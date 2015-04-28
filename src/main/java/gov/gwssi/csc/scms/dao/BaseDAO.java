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
}