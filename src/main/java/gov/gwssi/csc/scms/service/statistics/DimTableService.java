package gov.gwssi.csc.scms.service.statistics;

import gov.gwssi.csc.scms.dao.statistics.DimTableDAO;
import gov.gwssi.csc.scms.service.BaseService;
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

    public String getAllDimTableJsonData(){
        return dimTableDAO.getAllDimTableJsonData();
    }
}
