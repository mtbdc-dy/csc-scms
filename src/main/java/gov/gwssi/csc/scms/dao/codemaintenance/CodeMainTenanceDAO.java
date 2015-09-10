package gov.gwssi.csc.scms.dao.codemaintenance;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.query.CodeDetailResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lzs on 2015/7/13.
 * 代码维护
 */
@Service("codeMainTenanceDAO")
public class CodeMainTenanceDAO extends BaseDAO {
    public static final String PROJECTS = "dim_project";
    public static final String CONTINENTS = "dim_region";
    public static final String SUBJECTS = "dim_subject";
    public static final String ABNORMAL = "dim_anml";
    public static final String UNIVERSITIES = "dim_univ";
    public static final String TRANSLATE = "dim_translate";
    //获取代码维护列表
    public List getAllCodeList( String tableName,String chinaName) {
        List allCodeList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT t.seq,t.tableen,t.class,t.flag,t.tablech,t.type from v_dim_maintain t where 1 = 1");
        if(!"".equals(tableName)){
            stringBuilder.append(" and t.tableen like '%"+tableName+"%'");
        }
        if(!"".equals(chinaName)){
            stringBuilder.append("  and t.tablech like '%"+chinaName+"%'");
        }

        allCodeList = super.queryListBySql(stringBuilder.toString());
        return allCodeList;
    }
    //查询结果并返回
    public CodeDetailResult selectCode(CodeDetailResult codeDetailResult,String zdz) {
        String sql = "";
        List codeList = null;
        CodeDetailResult codeDetailResult1 = new CodeDetailResult();
        if(PROJECTS.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_project t where projectid = '"+zdz+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("projectid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("namech".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID(map.get("parentid".toUpperCase()).toString());
            System.out.println(map.get("projectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(TRANSLATE.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_translate t where translateid = '"+zdz+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("translateid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("namech".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID("");
            //System.out.println(map.get("projectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(CONTINENTS.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_region t where regionid = '"+zdz+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("regionid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("namech".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setPARENTID(map.get("parentid".toUpperCase()).toString());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());

            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(SUBJECTS.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_subject t where subjectid = '"+zdz+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("subjectid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("subjectnamech".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID(map.get("parentid".toUpperCase()).toString());
            System.out.println(map.get("subjectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(ABNORMAL.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_anml t where anmlid = '"+zdz+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("anmlid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("anml".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID(map.get("parentid".toUpperCase()).toString());
            System.out.println(map.get("anmlid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(UNIVERSITIES.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_univ t where univid = '"+zdz+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("univid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("UNIV".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID(map.get("province".toUpperCase()).toString());
            System.out.println(map.get("univid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else{
            return null;
        }


    }
    //查询结果并返回
    public CodeDetailResult selectCode(CodeDetailResult codeDetailResult) {
        String sql = "";
        List codeList = null;
        CodeDetailResult codeDetailResult1 = new CodeDetailResult();
        if(PROJECTS.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_project t where projectid = '"+codeDetailResult.getID()+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            codeDetailResult1.setID(map.get("projectid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("namech".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID(map.get("parentid".toUpperCase()).toString());
            System.out.println(map.get("projectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(TRANSLATE.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_translate t where translateid = '"+codeDetailResult.getID()+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("translateid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("namech".toUpperCase()).toString());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setPARENTID("");
            //System.out.println(map.get("projectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(CONTINENTS.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_region t where regionid = '"+codeDetailResult.getID()+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("regionid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("namech".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setPARENTID(map.get("parentid".toUpperCase()).toString());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            //System.out.println(map.get("projectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(SUBJECTS.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_subject t where subjectid = '"+codeDetailResult.getID()+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("subjectid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("subjectnamech".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID(map.get("parentid".toUpperCase()).toString());
            System.out.println(map.get("subjectid".toUpperCase()).toString());
            //System.out.println(map.get("projectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(ABNORMAL.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_anml t where anmlid = '"+codeDetailResult.getID()+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            codeDetailResult1.setID(map.get("anmlid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("anml".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID(map.get("parentid".toUpperCase()).toString());
            System.out.println(map.get("anmlid".toUpperCase()).toString());
            //System.out.println(map.get("projectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }else if(UNIVERSITIES.equals(codeDetailResult.getTABLEEN())){
            sql = "select * from dim_univ t where univid = '"+codeDetailResult.getID()+"'";
            codeList = super.queryListBySql(sql);
            HashMap map = (HashMap) codeList.get(0);
            SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            codeDetailResult1.setID(map.get("univid".toUpperCase()).toString());
            codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
            codeDetailResult1.setNAME(map.get("UNIV".toUpperCase()).toString());
            codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
            codeDetailResult1.setFULLNAME(map.get("updateby".toUpperCase()).toString());
            codeDetailResult1.setPARENTID(map.get("province".toUpperCase()).toString());
            System.out.println(map.get("univid".toUpperCase()).toString());
            //System.out.println(map.get("projectid".toUpperCase()).toString());
            try {
                codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(codeDetailResult1.getID());
            return codeDetailResult1;
        }
        return null;

    }
    //保存修改项

        public CodeDetailResult saveCode(CodeDetailResult codeDetailResult) {
        String sql = "";
        List codeList = null;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            CodeDetailResult codeDetailResult1 = new CodeDetailResult();
            if(PROJECTS.equals(codeDetailResult.getTABLEEN())){
                if("1".equals(codeDetailResult.getENABLED())){
                    sql="update dim_project t set t.namech ='"+codeDetailResult.getNAME()+"',t.enabled ='1',t.updateby='"+codeDetailResult.getFULLNAME()+"',t.updated =sysdate ,t.parentid = '"+codeDetailResult.getPARENTID()+"'where t.projectid = '"+codeDetailResult.getID()+"'";
                    int m = super.updateBySql(sql);
                    if(m==0){
                        throw  new RuntimeException("代码维护保存失败");
                    }else{
                        return codeDetailResult;
                    }
                }else{
                    List list = new ArrayList();
                    list.add(codeDetailResult.getID());
                    list.add("dim_project");
                    list.add(codeDetailResult.getFULLNAME());

                  String str =    super.doStatementForRtn("p_scms_delete_dim",list);
                    return codeDetailResult;
                }
            }else if (CONTINENTS.equals(codeDetailResult.getTABLEEN())){
                 if("1".equals(codeDetailResult.getENABLED())){
                     sql="update dim_region t set t.namech ='"+codeDetailResult.getNAME()+"',t.enabled ='1',t.updateby='"+codeDetailResult.getFULLNAME()+"',t.updated =sysdate where t.regionid = '"+codeDetailResult.getID()+"'";
                     int m = super.updateBySql(sql);
                    if(m==0){
                        throw  new RuntimeException("代码维护保存失败");
                    }else{
                        return codeDetailResult;
                    }
                }else{
                    List list = new ArrayList();
                    list.add(codeDetailResult.getID());
                    list.add("dim_region");
                    list.add(codeDetailResult.getFULLNAME());

                     String str = super.doStatementForRtn("p_scms_delete_dim",list);
                    return codeDetailResult;
                }
            }else if (SUBJECTS.equals(codeDetailResult.getTABLEEN())){
                if("1".equals(codeDetailResult.getENABLED())){
                    sql="update dim_subject t set t.SUBJECTNAMECH ='"+codeDetailResult.getNAME()+"',t.enabled ='1',t.updateby='"+codeDetailResult.getFULLNAME()+"',t.updated =sysdate where t.subjectid = '"+codeDetailResult.getID()+"'";
                    int m = super.updateBySql(sql);
                    if(m==0){
                        throw  new RuntimeException("代码维护保存失败");
                    }else{
                        return codeDetailResult;
                    }
                }else{
                    List list = new ArrayList();
                    list.add(codeDetailResult.getID());
                    list.add("dim_subject");
                    list.add(codeDetailResult.getFULLNAME());

                    String str =  super.doStatementForRtn("p_scms_delete_dim",list);
                    return codeDetailResult;
                }
            }else if (ABNORMAL.equals(codeDetailResult.getTABLEEN())){
                if("1".equals(codeDetailResult.getENABLED())){
                    sql="update dim_anml t set t.ANML ='"+codeDetailResult.getNAME()+"',t.enabled ='1',t.updateby='"+codeDetailResult.getFULLNAME()+"',t.updated =sysdate where t.anmlid = '"+codeDetailResult.getID()+"'";
                    int m = super.updateBySql(sql);
                    if(m==0){
                        throw  new RuntimeException("代码维护保存失败");
                    }else{
                        return codeDetailResult;
                    }
                }else{
                    List list = new ArrayList();
                    list.add(codeDetailResult.getID());
                    list.add("dim_anml");
                    list.add(codeDetailResult.getFULLNAME());

                    String str = super.doStatementForRtn("p_scms_delete_dim",list);
                    return codeDetailResult;
                }
            }else if (UNIVERSITIES.equals(codeDetailResult.getTABLEEN())){
                if("1".equals(codeDetailResult.getENABLED())){
                    sql="update dim_univ t set t.UNIV ='"+codeDetailResult.getNAME()+"',t.enabled ='1',t.updateby='"+codeDetailResult.getFULLNAME()+"',t.updated =sysdate where t.univid = '"+codeDetailResult.getID()+"'";
                    int m = super.updateBySql(sql);
                    if(m==0){
                        throw  new RuntimeException("代码维护保存失败");
                    }else{
                        return codeDetailResult;
                    }
                }else{
                    List list = new ArrayList();
                    list.add(codeDetailResult.getID());
                    list.add("dim_univ");
                    list.add(codeDetailResult.getFULLNAME());

                    String str = super.doStatementForRtn("p_scms_delete_dim",list);
                    return codeDetailResult;
                }
            }else if (TRANSLATE.equals(codeDetailResult.getTABLEEN())){
                if("1".equals(codeDetailResult.getENABLED())){
                    sql="update dim_translate t set t.NAMECH ='"+codeDetailResult.getNAME()+"',t.enabled ='1',t.updateby='"+codeDetailResult.getFULLNAME()+"',t.updated =sysdate where t.translateid = '"+codeDetailResult.getID()+"'";
                    int m = super.updateBySql(sql);
                    if(m==0){
                        throw  new RuntimeException("代码维护保存失败");
                    }else{
                        return codeDetailResult;
                    }
                }else{
                    List list = new ArrayList();
                    list.add(codeDetailResult.getID());
                    list.add("dim_translate");
                    list.add(codeDetailResult.getFULLNAME());
                    String str =  super.doStatementForRtn("p_scms_delete_dim",list);
                    return codeDetailResult;
                }

            }else{
                throw  new RuntimeException("代码维护保存失败");
            }
    }
    //新增
    @Transactional
    public String saveNewCode(CodeDetailResult codeDetailResult,String type) {
        String sql = "",zdz = "";
        List codeList = null,zdList =null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        CodeDetailResult codeDetailResult1 = new CodeDetailResult();
        if(TRANSLATE.equals(codeDetailResult.getTABLEEN())){
            zdz =super.getDicIdByClassType(type);
            sql = "insert into "+codeDetailResult.getTABLEEN()+" values('"+type+"',f_scms_dim_id('"+type+"'),'"+codeDetailResult.getNAME()+"','','"+codeDetailResult.getENABLED()+"','"+codeDetailResult.getFULLNAME()+"',SYSDATE)";
            int n = super.updateBySql(sql);
            if (n==1) {


                return zdz;
            }
            return "";
        }else if(PROJECTS.equals(codeDetailResult.getTABLEEN())){
            zdz =super.getDicIdByClassType(type);
            sql = "insert into "+codeDetailResult.getTABLEEN()+" values(f_scms_dim_id('"+type+"'),'','"+codeDetailResult.getNAME()+"','"+codeDetailResult.getPARENTID()+"','"+type+"','"+codeDetailResult.getENABLED()+"','"+codeDetailResult.getFULLNAME()+"',SYSDATE)";
            int n = super.updateBySql(sql);
            if (n==1) {
               return zdz;
            }
            return "";
        }else if(CONTINENTS.equals(codeDetailResult.getTABLEEN())){
            zdz =super.getDicIdByClassType(type);
            sql = "insert into "+codeDetailResult.getTABLEEN()+" values(f_scms_dim_id('"+type+"'),'','"+codeDetailResult.getNAME()+"','"+codeDetailResult.getPARENTID()+"','"+type+"','"+codeDetailResult.getENABLED()+"','"+codeDetailResult.getFULLNAME()+"',SYSDATE)";
            int n = super.updateBySql(sql);
            if (n==1) {
                return zdz;
            }
            return "";

        }else if(SUBJECTS.equals(codeDetailResult.getTABLEEN())){
            zdz =super.getDicIdByClassType(type);
            sql = "insert into "+codeDetailResult.getTABLEEN()+" values(f_scms_dim_id('"+type+"'),'"+codeDetailResult.getNAME()+"','','"+type+"','"+codeDetailResult.getPARENTID()+"','"+codeDetailResult.getENABLED()+"','"+codeDetailResult.getFULLNAME()+"',SYSDATE)";
            int n = super.updateBySql(sql);
            if (n==1) {
                return zdz;
            }
            return "";
        }else if(ABNORMAL.equals(codeDetailResult.getTABLEEN())){
            zdz =super.getDicIdByClassType(type);
            sql = "insert into "+codeDetailResult.getTABLEEN()+" values(f_scms_dim_id('"+type+"'),'"+codeDetailResult.getNAME()+"','"+codeDetailResult.getPARENTID()+"','"+type+"','"+codeDetailResult.getENABLED()+"','"+codeDetailResult.getFULLNAME()+"',SYSDATE)";
            int n = super.updateBySql(sql);
            if (n==1) {
                return zdz;
            }
            return "";
        }else if(UNIVERSITIES.equals(codeDetailResult.getTABLEEN())){
            zdz =super.getDicIdByClassType(type);
            sql = "insert into "+codeDetailResult.getTABLEEN()+" (UNIVID,UNIV,PROVINCE,ENABLED,UPDATEBY,UPDATED) values(f_scms_dim_id('"+type+"'),'"+codeDetailResult.getNAME()+"','"+codeDetailResult.getPARENTID()+"','"+codeDetailResult.getENABLED()+"','"+codeDetailResult.getFULLNAME()+"',SYSDATE)";
            int n = super.updateBySql(sql);
            if (n==1) {
                return zdz;
            }
            return "";
        }else{
            return "";
        }


    }
    //获取父节点
    public List getParentCode(String type) {
        String type1 = type;
        List codeList = null;
        String sql = "";


            sql = "select t.parentid code,t.parentname value from v_dim_parentid t where t.classid = '"+type+"'";
             codeList = super.queryListBySql(sql);

                return codeList;
    }
    //获取详细的代码维护数据
    public List getDetailCodeList(String seq,String tableName,String flag) {
        String id = seq;
        List seqList = null,codeList = null;
        String sql = "";
        String codeSql = "";
        if("".equals(id)){
            return codeList;
        }else{
            sql = "select t.sql from v_dim_maintain t where t.seq = '"+id+"'";
            seqList = super.queryListBySql(sql);
            if(seqList.size()>0){
                codeSql = ((HashMap)seqList.get(0)).get("SQL").toString();
                codeList = super.queryListBySql(codeSql);
                System.out.println();
                return codeList;
            } else{
                return codeList;
            }

        }

    }
}
