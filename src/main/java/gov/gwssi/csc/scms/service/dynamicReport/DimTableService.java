package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.dao.statistics.DimTableDAO;
import gov.gwssi.csc.scms.dao.statistics.TablesDAO;
import gov.gwssi.csc.scms.domain.dynamicReport.TablesJson;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.dictionary.NoSuchDictTreeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * DimTable Service层
 */

@Service
public class DimTableService extends BaseService {

    @Autowired
    private DimTableDAO dimTableDAO;

    @Autowired
    private TablesDAO tablesDAO;

    public String getAllDimTableJsonData(){
        return dimTableDAO.getAllDimTableJsonData();
    }

    // 获取tables及tables下的字段列表
    public List<TablesJson> getTables() throws NoSuchDictTreeException{
        List<TablesJson> tablesJson = null;
        tablesJson = tablesDAO.getTables();
        if(tablesJson == null){
            throw new NoSuchDictTreeException("can not find the tablesJsons");
        }
        return tablesJson;
    }

}
