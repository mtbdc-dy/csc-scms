package gov.gwssi.csc.scms.dao.codemaintenance;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.query.CodeDetailResult;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lzs on 2015/7/13.
 * 代码维护
 */
@Service("codeMainTenanceDAO")
public class CodeMainTenanceDAO extends BaseDAO {
    //获取代码维护列表
    public List getAllCodeList( String tableName,String chinaName) {
        List allCodeList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT t.seq,t.tableen,t.class,t.flag,t.tablech,t.sql from v_dim_maintain t where 1 = 1");
        if(!"".equals(tableName)){
            stringBuilder.append(" and t.tableen like '%"+tableName+"%'");
        }
        if(!"".equals(chinaName)){
            stringBuilder.append("  and t.class like '%"+chinaName+"%'");
        }

        allCodeList = super.queryListBySql(stringBuilder.toString());
        return allCodeList;
    }
    //保存修改项

        public CodeDetailResult saveCode(CodeDetailResult codeDetailResult) {
            String sql = "";
            List codeList = null;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            CodeDetailResult codeDetailResult1 = new CodeDetailResult();
            sql = "update " +codeDetailResult.getTABLEEN()+" t set t.namech ='"+codeDetailResult.getNAME()+"',t.enabled ='"+codeDetailResult.getENABLED()+"',t.updateby='"+codeDetailResult.getFULLNAME()+"',t.updated =sysdate where t.translateid = '"+codeDetailResult.getID()+"'";
           //System.out.println("--==" + sql);
            int m = super.updateBySql(sql);
            if(m==0){
                throw  new RuntimeException("代码维护保存失败");
            }else{
                sql = "select * from dim_translate t where translateid = '"+codeDetailResult.getID()+"'";
                codeList = super.queryListBySql(sql);
                HashMap map = (HashMap) codeList.get(0);
                SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(map.get("updated".toUpperCase()).toString());
                try {
                    System.out.println(sdf.parse(map.get("updated".toUpperCase()).toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                codeDetailResult1.setID(map.get("translateid".toUpperCase()).toString());
                codeDetailResult1.setENABLED(map.get("enabled".toUpperCase()).toString());
                codeDetailResult1.setNAME(map.get("namech".toUpperCase()).toString());
                codeDetailResult1.setTABLEEN(codeDetailResult.getTABLEEN());
                System.out.println(map.get("translateid".toUpperCase()).toString());
                try {
                    codeDetailResult1.setUPDATED(sdf.parse(map.get("updated".toUpperCase()).toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(codeDetailResult1.getID());
                return codeDetailResult1;
            }


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
                return codeList;
            } else{
                return codeList;
            }

        }
//        List detailCodeList = null;
//       // StringBuilder stringBuilder = new StringBuilder();
//String rtu = "";
//        String sql = "";
//       if("0".equals(flag)){
//            sql = "select dim.* from dim_translate dim " +
//                    "where dim.classid = ( select d.classid from v_dim_maintain t, dim_class d where t.class = d.classch and t.seq = "+id+" )";
//           detailCodeList = super.queryListBySql(sql);
//        }

//if(detailCodeList.size()>0){
//    rtu = "[";
//    for(int i = 0;i<detailCodeList.size();i++){
//String modDate ="",modPeople = "";
//        if(((Map)detailCodeList.get(i)).get("UPDATEBY")!=null){
//            modPeople = ((Map)detailCodeList.get(i)).get("UPDATEBY").toString();
//        }
//        if(((Map)detailCodeList.get(i)).get("UPDATEBD")!=null){
//            modDate = ((Map)detailCodeList.get(i)).get("UPDATEBD").toString();
//        }
//        rtu = rtu+"{ \"TRANSLATEID\":\""+((Map)detailCodeList.get(i)).get("TRANSLATEID")+"\",\"NAMECH\":\""+((Map)detailCodeList.get(i)).get("NAMECH")+"\",\"ENABLED\":\""+((Map)detailCodeList.get(i)).get("ENABLED")+"\"," +
//                " \"UPDATEBY\":\""+modPeople+"\",\"UPDATED\":\""+modDate+"\"}";
//        if(i!=detailCodeList.size()-1){
//            rtu = rtu+",";
//        }
//    }
//
//    rtu = rtu+"]";
//    return rtu;
//}else{
//    return "[]";
//}

    }
}
