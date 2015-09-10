package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.DTableList;
import gov.gwssi.csc.scms.repository.dynamicReport.DTableListRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * DTableListService Service层
 */
@Service("dTableListService")
public class DTableListService extends BaseService{
    @Autowired
    @Qualifier("dTableListRepository")
    private DTableListRepository dTableListRepository;
    public List<DTableList> findByConfigId(String configId){
        return dTableListRepository.findByConfigId(configId);
    }
}
