package gov.gwssi.csc.scms.dao.statistics;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * DimColumn DAO层封装类
 */
@Service("dimColumnDAO")
public class DimColumnDAO extends BaseDAO{

    public List getDimColumnByTableEn(String tableEn){
        List dimColumnList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT TABLEEN,TABLECH,COLUMNEN,COLUMNCH,DATATYPE,REFERENCETABLEEN,REFERENCETABLECH,REFERENCECOLUMNEN,REFERENCECOLUMNCH FROM DIM_COLUMN WHERE TABLEEN='"+tableEn+"'");
        dimColumnList = super.queryListBySql(stringBuilder.toString());
        return dimColumnList;
    }

    public String getDimColumnJsonDataByTableEn(String tableEn){
        List dimColumnList = getDimColumnByTableEn(tableEn);
        String jsonData = JsonMapper.getInstance().toJson(dimColumnList);
        return jsonData;
    }

}
