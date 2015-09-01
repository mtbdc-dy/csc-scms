package gov.gwssi.csc.scms.dao.statistics;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dynamicReport.Header;
import gov.gwssi.csc.scms.domain.dynamicReport.Merge;
import gov.gwssi.csc.scms.domain.dynamicReport.Position;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/6/9.
 */

@Service("dStatisticsDAO")
public class DStatisticsDAO extends BaseDAO{

    /**
     * 根据sequence name 生成 id
     */
    public String generateIdBySequence(String sequenceName){
       return super.getIdBySequence(sequenceName);
    }

    /**
     * 调用存储过程p_scms_d_Statistics(avc_configid in varchar2,avc_result out varchar2)
     */
    public String invokeProcedureDStatistics(String configId){
        return super.invokeProcedureByProcedureName(configId,"p_scms_d_Statistics");
    }

    /**
     * 根据配置编号获取Header信息
     */
    public List<Header> getHeaders(String configId){

        List<Header> headers = new ArrayList<Header>();
        Position position = null;
        Merge merge = null;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select t1.value,t2.xcoordinate1,t2.ycoordinate1,t2.xcoordinate2,t2.ycoordinate2," +
                "t2.xmerge,t2.ymerge from scms_d_header t1,scms_d_headermerge t2 where t1.id = t2.id and t1.configid = t2.configid" +
                "and t1.configid = " + configId);
        List list = super.queryListBySql(stringBuilder.toString());

        if(list != null){
           for(int i=0;i<list.size();i++){
               Map map = (Map)list.get(i);
               String value = (String)map.get("VALUE");
               int rowIndex = Integer.parseInt((String)map.get("XCOORDINATE1"));
               int colIndex = Integer.parseInt((String)map.get("YCOORDINATE1"));
               int rowspan = Integer.parseInt((String)map.get("XMERGE"));
               int colspan = Integer.parseInt((String)map.get("YMERGE"));

               position = new Position();
               position.setRowIndex(rowIndex);
               position.setColIndex(colIndex);

               merge = new Merge();
               merge.setRowspan(rowspan);
               merge.setColspan(colspan);

               Header header = new Header();
               header.setValue(value);
               header.setPostion(position);
               header.setMerge(merge);

               headers.add(header);

           }
        }

        return headers;
    }


}
