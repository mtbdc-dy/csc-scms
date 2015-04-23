package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.BasicInfo;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.BasicInfoRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("basicInfoService")
public class BasicInfoService extends BaseService {

    @Autowired
    @Qualifier("basicInfoRepository")
    private BasicInfoRepository basicInfoRepository;

    public BasicInfo getBasicInfoById(Long id) {
        return basicInfoRepository.findOne(id);
    }

    public BasicInfo getBasicInfoByStudentId(Long studentId) {
        return basicInfoRepository.findByStudentId(studentId);
    }

    public BasicInfo updateBasicInfo(BasicInfo basicInfo) {
        BasicInfo basicInfoTemp = getBasicInfoById(basicInfo.getId());
        copyFiledValue(BasicInfo.class,basicInfo,basicInfoTemp);
        return basicInfoRepository.save(basicInfoTemp);
    }

    public BasicInfo saveBasicInfo(BasicInfo basicInfo) {
        return basicInfoRepository.save(basicInfo);
    }
}
