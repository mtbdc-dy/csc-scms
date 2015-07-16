package gov.gwssi.csc.scms.dao.codemaintenance;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        stringBuilder.append("SELECT t.seq,t.tableen,t.class,t.flag,t.tablech from v_dim_maintain t where 1 = 1");
        if(!"".equals(tableName)){
            stringBuilder.append(" and t.tableen like '%"+tableName+"%'");
        }
        if(!"".equals(chinaName)){
            stringBuilder.append("  and t.class like '%"+chinaName+"%'");
        }

        allCodeList = super.queryListBySql(stringBuilder.toString());
        return allCodeList;
    }
    //获取详细的代码维护数据
    public String getDetailCodeList(String id,String tableName,String flag) {
        List detailCodeList = null;
       // StringBuilder stringBuilder = new StringBuilder();
String rtu = "";
        String sql = "";
       if("0".equals(flag)){
            sql = "select dim.* from dim_translate dim " +
                    "where dim.classid = ( select d.classid from v_dim_maintain t, dim_class d where t.class = d.classch and t.seq = "+id+" )";
           detailCodeList = super.queryListBySql(sql);
        }
if(detailCodeList.size()>0){
    rtu = "[";
    for(int i = 0;i<detailCodeList.size();i++){
rtu = rtu+"{ \'TRANSLATEID\':\'"+((Map)detailCodeList.get(i)).get("TRANSLATEID")+"\',\'NAMECH\':\'"+((Map)detailCodeList.get(i)).get("NAMECH")+"\',\'ENABLED\':\'"+((Map)detailCodeList.get(i)).get("ENABLED")+"\'," +
        " \'UPDATEBY\':\'"+((Map)detailCodeList.get(i)).get("UPDATEBY")+"\',\'UPDATED\':\'"+((Map)detailCodeList.get(i)).get("UPDATED")+"\'}";
    if(i!=detailCodeList.size()-1){
        rtu = rtu+",";
    }
    }

    rtu = rtu+"]";
    return rtu;
}else{
    return "[]";
}

    }
}
