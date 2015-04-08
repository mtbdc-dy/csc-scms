package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.BasicInfo;
import gov.gwssi.csc.scms.repository.student.BasicInfoRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("basicInfoService")
public class BasicInfoService extends BaseService {

    @Autowired
    private BasicInfoRepository basicInfoRepository;

    public BasicInfo getBasicInfoById(Long id) {
        return basicInfoRepository.findOne(id);
    }

    public BasicInfo saveBasicInfo(BasicInfo basicInfo) {
        return basicInfoRepository.save(basicInfo);
    }
}
