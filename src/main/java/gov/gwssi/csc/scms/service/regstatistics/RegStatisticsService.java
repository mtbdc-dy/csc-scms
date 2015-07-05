package gov.gwssi.csc.scms.service.regstatistics;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.AbnormalResultObject;
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
    public List<RegStatistics> getRegStatistics(String type, String province,
                    String university,String arrivalDateBegin,String arrivalDateEnd) {
        List listParameter = new ArrayList();
        listParameter.add(type);
        listParameter.add(province);
        listParameter.add(university);
        listParameter.add(arrivalDateBegin);
        listParameter.add(arrivalDateEnd);
        //返回id 根据id查询 wangrui
        String sameId = getBaseDao().doStatementForRtn("p_scms_stats_register", listParameter);
        StringBuilder sb = new StringBuilder();
//        sb.append(RegStatistics.getResultObject());
//        sb.append(" from RegStatistics rs where rs.sameId = '");
        sb.append(" select rs.id, rs.sameId, rs.no, rs.province, rs.university, rs.totalNum," +
                " rs.registeredNum, rs.giveUpNum, rs.unhandledNum from SCMS_STATS_REGISTER rs where rs.sameId = '");
        sb.append(sameId).append("' order by rs.no");
//        List<RegStatistics> regStatisticsList = super.getBaseDao().getObjectListByHQL(sb.toString(), RegStatistics.class);
        List<RegStatistics> regStatisticsList = super.getBaseDao().queryTListBySql(sb.toString(), RegStatistics.class);

        return regStatisticsList;
    }


}
