package gov.gwssi.csc.scms.dao.codemaintenance;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzs on 2015/7/13.
 * ´úÂëÎ¬»¤
 */
@Service("codeMainTenanceDAO")
public class CodeMainTenanceDAO extends BaseDAO {
    public List getAllCodeList( String tableName,String chinaName) {
        List allCodeList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * from v_dim_maintain t where 1 = 1");
        if(!"".equals(tableName)){
            stringBuilder.append(" and t.tableen like '%"+tableName+"%'");
        }
        if(!"".equals(chinaName)){
            stringBuilder.append("  and t.class like '%"+chinaName+"%'");
        }

        allCodeList = super.queryListBySql(stringBuilder.toString());
        return allCodeList;
    }
}
