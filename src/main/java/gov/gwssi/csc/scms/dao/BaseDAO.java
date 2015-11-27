package gov.gwssi.csc.scms.dao;

import gov.gwssi.csc.scms.domain.user.Project;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public List<Map> queryListBySql(String sql) {
        List<Map> objectList;
        EntityManager em = null;

        try {
            em = entityManagerFactory.createEntityManager();
            //创建原生SQL查询QUERY实例
            em.getTransaction().begin();
            Query query = em.createNativeQuery(sql);
            //list转为List<Map>
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            objectList = query.getResultList();
            em.getTransaction().commit();
            return objectList;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public <T> List<T> queryTListBySql(String sql, Class<T> clazz) {
        List<T> objectList;
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            //创建原生SQL查询QUERY实例
            return (List<T>) em.createNativeQuery(sql, clazz).getResultList();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public <T> List<T> getObjectListByHQL(String hSql, Class<T> clazz) {
        EntityManager em = null;
        List<T> list;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery(hSql);
            list = query.getResultList();
            return list;
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        String callName = "{ call " + name + " (\'"; // call存储过程名
        for (int i = 0; i < list.size() - 1; i++) { // 取参数的问号
            callName = callName + list.get(i) + "\',\'";
//            System.out.println(callName);
        }
        callName = callName + list.get(list.size() - 1) + "\') }";

        jdbcTemplate.execute(callName);

    }

    public String doStatementForRtn(final String name, final List list) {
        String callName = "{call " + name + " (";
        for (int j = 0; j < list.size(); j++) {
            callName = callName + "?, ";
        }
        callName = callName + "?) }";
        System.out.println("callName===" + callName);
        Object returnValue = getJdbcTemplate().execute(callName,
                new CallableStatementCallback() {
                    public Object doInCallableStatement(CallableStatement cs)
                            throws SQLException, DataAccessException {
                        String outstr = null;
                        try {
                            for (int k = 0; k < list.size(); k++) {
                                Object o = list.get(k);
                                if (o instanceof String) {
                                    cs.setString(k + 1, o.toString());
                                } else if (o instanceof java.util.Date) {
                                    java.sql.Date d = new java.sql.Date(
                                            ((java.util.Date) o).getTime());
                                    cs.setDate(k + 1, d);
                                } else {
                                    cs.setString(k + 1, o.toString());
                                }
                            }
                            int outint = list.size() + 1;
                            cs.registerOutParameter(outint, Types.VARCHAR);
                            cs.execute();
                            outstr = cs.getString(outint);
                            return outstr;
                        } catch (Exception excpt) {
                            excpt.printStackTrace();
                            try {
                                throw new Exception(excpt);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        return outstr;
                    }
                });

        return (String) returnValue;
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

    public String getDicIdByClassType(String classType) {
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
     * 根据nameCH查询dim_region表得到regionId
     */
    public String getNameCHByRegionId(String regionId) {
        String sql = "select NAMECH from DIM_REGION where REGIONID='" + regionId + "'";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return String.valueOf(query.getSingleResult());
            } else {
                return "-";
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    /**
     * 根据univId查询dim_univ表得到univ
     */
    public String getUnivByUnivId(String univId) {
        String sql = "select UNIV from DIM_UNIV where UNIVID='" + univId + "'";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return String.valueOf(query.getSingleResult());
            } else {
                return "-";
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }


    /**
     * 根据TranslateId查询dim_translate表得到中文名称
     */
    public String getNameCHByTranslateId(String translateId) {
//        System.out.println(translateId);
        String sql = "select NAMECH from DIM_TRANSLATE where TRANSLATEID='" + translateId + "'";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return String.valueOf(query.getSingleResult());
            } else {
                return "-";
            }

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
            //em.joinTransaction();
            //创建原生SQL查询QUERY实例
            em.getTransaction().begin();
            Query query = em.createNativeQuery(sql);
            int num = query.executeUpdate();
            em.getTransaction().commit();
//            em.flush();
            return num;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    /**
     * 调用存储过程procedure p_scms_d_Statistics(avc_configid in varchar2,avc_result out varchar2)
     */
    public String invokeProcedureByProcedureName(String in, String procedureName) {
        EntityManager em = null;
        em = entityManagerFactory.createEntityManager();
        try {
            StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery(procedureName);

            storedProcedureQuery.registerStoredProcedureParameter("avc_configid", String.class, ParameterMode.IN);
            storedProcedureQuery.registerStoredProcedureParameter("avc_result", String.class, ParameterMode.OUT);

            storedProcedureQuery.setParameter("avc_conigid", in);
            boolean execute = storedProcedureQuery.execute();

            String result = (String) storedProcedureQuery.getOutputParameterValue("avc_result");

            return result;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //得到用户密码
    public String getPWDByUserId(String userId) {
        String sql = "select PASSWORD from PUB_USER where USERID='" + userId + "'";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return String.valueOf(query.getSingleResult());
            } else {
                return "-";
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //统计机票 主管用户
    public int getTicketStatusNumZG(List<Project> projects,String state) {
        String finalDate = null;
        String intialDate = null;
        int currentYear=0;
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        intialDate = currentYear + "-01-01";
        finalDate = currentYear + "-12-31";
        String proSql = "";
        for (int i = 0; i < projects.size(); i++) {
            proSql = proSql + "'" + projects.get(i).getProjectId() + "',";
        }
        String pSql = proSql.substring(0, proSql.length() - 1) + ")";
        String sql = "select count(t.ID) from SCMS_AIRTICKET t,SCMS_BASIC_INFO b where t.studentid = b.studentid and b.projectname in (" + pSql + " and t.STATE = '"+ state +"' and t.CREATED >= to_date('"+ intialDate + "','yyyy-mm-dd') and t.CREATED <= to_date('"+ finalDate +"','yyyy-mm-dd')";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return Integer.valueOf(String.valueOf(query.getSingleResult()));
            } else {
                return 0;
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //统计机票 学校用户
    public int getTicketStatusNumS(String school,String state) {
        String sql = "select count(t.ID) from SCMS_AIRTICKET t,SCMS_SCHOOLROLL b where t.studentid = b.studentid and b.CURRENTUNIVERSITY ='" + school + "' and t.STATE = '"+ state +"'";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return Integer.valueOf(String.valueOf(query.getSingleResult()));
            } else {
                return 0;
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    //统计机票
    public int getTicketStatusNum(String state) {
        String finalDate = null;
        String intialDate = null;
        int currentYear=0;
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        intialDate = currentYear + "-01-01";
        finalDate = currentYear + "-12-31";
        String sql = "select count(t.ID) from SCMS_AIRTICKET t where t.STATE = '"+ state +"' and t.CREATED >= to_date('"+ intialDate + "','yyyy-mm-dd') and t.CREATED <= to_date('"+ finalDate +"','yyyy-mm-dd')";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return Integer.valueOf(String.valueOf(query.getSingleResult()));
            } else {
                return 0;
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //统计异动  主管用户
    public int getAbnormalZG(List<Project> projects,String state){
        String proSql = "";
        for (int i = 0; i < projects.size(); i++) {
            proSql = proSql + "'" + projects.get(i).getProjectId() + "',";
        }
        String pSql = proSql.substring(0, proSql.length() - 1) + ")";
        String sql = "select count(t.ID) from SCMS_ABNORMAL t,SCMS_BASIC_INFO b where t.studentid = b.studentid and b.projectname in (" + pSql + " and t.STATE = '"+ state + "'";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return Integer.valueOf(String.valueOf(query.getSingleResult()));
            } else {
                return 0;
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //统计异动  基金委非主管用户
    public int getAbnormal(String state){
        String sql = "select count(t.ID) from SCMS_ABNORMAL t where t.STATE = '"+ state + "'";
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return Integer.valueOf(String.valueOf(query.getSingleResult()));
            } else {
                return 0;
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //统计奖学金审批已上报数量
    public int getScholarshipSubmited(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String sql = "select count(t.ID) from SCMS_SCHOLARSHIP t where t.schoolsta = '1' and t.cscsta = '0' and t.schoolqual + t.schoolunqual <> 0 and t.year = " + year;
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query query = em.createNativeQuery(sql);
            if (query.getSingleResult() != null) {
                return Integer.valueOf(String.valueOf(query.getSingleResult()));
            } else {
                return 0;
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}