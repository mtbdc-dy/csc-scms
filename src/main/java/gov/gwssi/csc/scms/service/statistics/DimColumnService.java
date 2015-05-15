package gov.gwssi.csc.scms.service.statistics;

import gov.gwssi.csc.scms.dao.statistics.DimColumnDAO;
import gov.gwssi.csc.scms.domain.statistics.DimColumn;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * DimColumn Service层
 */
@Service("dimColumnService")
public class DimColumnService extends BaseService{
    @Autowired
    private DimColumnDAO dimColumnDAO;

    public String  getDimColumnJsonDataByTableEn(String tableEn){
        return dimColumnDAO.getDimColumnJsonDataByTableEn(tableEn);
    }

}
