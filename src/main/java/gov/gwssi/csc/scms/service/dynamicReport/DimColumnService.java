package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.dao.statistics.DimColumnDAO;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangZhenghua on 2015/5/14.
 * DimColumn Service层
 */
@Service("dimColumnService")
public class DimColumnService extends BaseService{
    @Autowired
    private DimColumnDAO dimColumnDAO;

    public String  getDimColumnJsonDataByTableEn(String tableEn){
        String jsonData = "";
        if(tableEn != null && !tableEn.equals("")){
            jsonData = dimColumnDAO.getDimColumnJsonDataByTableEn(tableEn);
        }
        return jsonData;
    }

}
