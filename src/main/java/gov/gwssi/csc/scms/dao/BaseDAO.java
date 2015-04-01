package gov.gwssi.csc.scms.dao;

import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import org.hibernate.SQLQuery;
import org.hibernate.mapping.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Wang Rui on 2015/3/30.
 */
//@Repository
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
        List<Map> objectArrayList = new ArrayList();
        EntityManager em = entityManagerFactory.createEntityManager();
        //创建原生SQL查询QUERY实例
        Query query =  em.createNativeQuery(sql);
//        list转为List<Map>
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        objectArrayList =query.getResultList();
        System.out.println("objectArrayList="+objectArrayList);
        em.close();
        return objectArrayList;
    }

    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}
