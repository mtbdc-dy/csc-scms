package gov.gwssi.csc.scms.service.regstatistics;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.regstatistics.RegStatistics;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.log.OperationLogRepository;
import gov.gwssi.csc.scms.repository.regstatistics.RegStatisticsRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.NoSupportedUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wang Rui on 2015/6/22.
 */
@Service("regStatisticsService")
public class RegStatisticsService extends BaseService {
    @Autowired
    @Qualifier("regStatisticsRepository")
    private RegStatisticsRepository regStatisticsRepository;

    @SuppressWarnings("unchecked")
    public List<RegStatistics> getRegStatistics(String province,
                    String university,String arrivalDateBegin,String arrivalDateEnd) {
        List listParameter = new ArrayList();
        listParameter.add(province);
        listParameter.add(university);
        listParameter.add(arrivalDateBegin);
        listParameter.add(arrivalDateEnd);
        //����id ����id��ѯ
        String id = null;
        getBaseDao().doStatement("p_scms_stats_register", listParameter);
        return regStatisticsRepository.findById(id);
    }


}
