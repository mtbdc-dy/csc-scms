package gov.gwssi.csc.scms.dao.statistics;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * DIMTable DAO层封装类
 */

@Service("dimTableDAO")
public class DimTableDAO extends BaseDAO{

    private final String RETURN_BLANK_JSON = "[]";

    public List getAllDimTable(){
        List dimTableList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT TABLEEN,TABLECH,TABLETYPE FROM DIM_TABLE");
        dimTableList = super.queryListBySql(stringBuilder.toString());
        return dimTableList;
    }

    public String getAllDimTableJsonData(){
        List dimTableList = null;
        String jsonData = "";
        dimTableList = getAllDimTable();
        if(dimTableList != null){
            jsonData = JsonMapper.getInstance().toJson(dimTableList);
        }else{
            return RETURN_BLANK_JSON;
        }
        return jsonData;
    }

}
