package gov.gwssi.csc.scms.dao.statistics;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.statistics.TablesJson;
import gov.gwssi.csc.scms.domain.statistics.ValueObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/5/29.
 * Tables DAO层封装类
 */
@Service("TablesDAO")
public class TablesDAO  extends BaseDAO {

    public List<TablesJson> getTables() {

        // 返回的整体的tables资源
        List<TablesJson> totalTablesJson = new ArrayList<TablesJson>();

        // 获取所有tables
        List<Map> dimTableList = getAllDimTable();

        // 遍历dimTableList,进行转义
        for(int i=0;i<dimTableList.size();i++){

            Map map = (Map)dimTableList.get(i);
            TablesJson tablesJson = new TablesJson();

            String tableEn = (String)map.get("TABLEEN");
            String tableCh = (String)map.get("TABLECH");
            String tableType = (String)map.get("TABLETYPE");

            tablesJson.setCode(tableEn);
            tablesJson.setCodePid("T0000");
            tablesJson.setValid(true);

            ValueObject valueObject = new ValueObject();
            valueObject.setName(tableCh);
            valueObject.setTableType(tableType);
            valueObject.setDataType(null);

            tablesJson.setValue(valueObject);

            List<TablesJson> children = new ArrayList<TablesJson>();

            List<Map> childList = getDimColumnByTableEn(tableEn);

            for(int j=0;j<childList.size();j++){

                map = childList.get(j);

                TablesJson childTableJson = new TablesJson();

                String columnEn = (String)map.get("COLUMNEN");
                String dataType = (String)map.get("DATATYPE");
                String columnCh = (String)map.get("COLUMNCH");

                childTableJson.setCode(columnEn);
                childTableJson.setCodePid(tableEn);
                childTableJson.setValid(true);

                ValueObject childValueObject = new ValueObject();
                childValueObject.setName(columnCh);
                childValueObject.setTableType(null);
                childValueObject.setDataType(dataType);

                childTableJson.setValue(childValueObject);

                children.add(childTableJson);
            }

            tablesJson.setChildren(children);
            totalTablesJson.add(tablesJson);
        }

        return totalTablesJson;
    }

    // 获取所有可配置的DIM_TABLE列表
    public List<Map> getAllDimTable() {
        return super.queryListBySql("SELECT TABLEEN,TABLECH,TABLETYPE FROM DIM_TABLE");
    }

    // 根据表名获取可配置的字段列表
    public List<Map> getDimColumnByTableEn(String tableEn) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT TABLEEN,TABLECH,COLUMNEN,COLUMNCH,DATATYPE,REFERENCETABLEEN,REFERENCETABLECH,REFERENCECOLUMNEN,REFERENCECOLUMNCH FROM DIM_COLUMN WHERE TABLEEN='" + tableEn + "'");
        return super.queryListBySql(stringBuilder.toString());
    }


}
