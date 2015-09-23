package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.BasicInfo;
import gov.gwssi.csc.scms.repository.student.BasicInfoRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("basicInfoService")
public class BasicInfoService extends BaseService {

    @Autowired
    @Qualifier("basicInfoRepository")
    private BasicInfoRepository basicInfoRepository;
    @Autowired
    private OperationLogService operationLogService;

    public BasicInfo getBasicInfoById(String id) {
        return basicInfoRepository.findOne(id);
    }

    public BasicInfo getBasicInfoByStudentId(String studentId) {
        return basicInfoRepository.findByStudentId(studentId);
    }

    public BasicInfo updateBasicInfo(BasicInfo basicInfo) {
        basicInfo.setStudent(getBasicInfoById(basicInfo.getId()).getStudent());
        return basicInfoRepository.save(basicInfo);
    }

    @Transactional
    public BasicInfo updateBasicInfo(BasicInfo basicInfo,List<OperationLog> operationLogs) {
        operationLogService.saveOperationLog(operationLogs);
        basicInfo.setStudent(getBasicInfoById(basicInfo.getId()).getStudent());
        return basicInfoRepository.save(basicInfo);
    }

    public BasicInfo saveBasicInfo(BasicInfo basicInfo) {
        String basicInfoId = getBaseDao().getIdBySequence("SEQ_BASIC_INFO");
        basicInfo.setId(basicInfoId);
        return basicInfoRepository.save(basicInfo);
    }
}
