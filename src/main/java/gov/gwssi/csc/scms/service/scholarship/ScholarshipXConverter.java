package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.student.BasicInfo;
import gov.gwssi.csc.scms.domain.student.SchoolRoll;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianj on 2015/8/31.
 */
public class ScholarshipXConverter extends BaseService implements Converter<ScholarshipX, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(ScholarshipX scholarshipX) {
        Map<String, Object> target = new HashMap<String, Object>();
        Student student = scholarshipX.getStudent();
        BasicInfo basicInfo = student.getBasicInfo();
        SchoolRoll schoolRoll = student.getSchoolRoll();
        target.put("id",scholarshipX.getId());
        target.put("scholarshipId",scholarshipX.getScholarshipId());
        target.put("studentId",student.getId());
        target.put("year",scholarshipX.getYear());
        target.put("cscId",student.getCscId());
        target.put("gender",basicInfo.getGender());
        target.put("passportName",basicInfo.getPassportName());
        target.put("birthday",basicInfo.getBirthday());
        target.put("schReview",scholarshipX.getSchReview());
        target.put("schResult",scholarshipX.getSchResult());
        target.put("cscReview",scholarshipX.getCscReview());
        target.put("cscResult",scholarshipX.getCscResult());
        target.put("schReason",scholarshipX.getSchReason());
        target.put("schStartTime",scholarshipX.getSchStartTime());
        target.put("schEndTime",scholarshipX.getSchEndTime());
        target.put("cscReason",scholarshipX.getCscReason());
        target.put("cscStartTime",scholarshipX.getCscStartTime());
        target.put("updated",scholarshipX.getUpdated());
        target.put("updateby",scholarshipX.getUpdateby());
        target.put("cscEndTime",scholarshipX.getCscEndTime());
        target.put("cscrresult_lastyear",scholarshipX.getCscrresult_lastyear());
        target.put("schoolSta",scholarshipX.getSchoolSta());
        target.put("cscSta",scholarshipX.getCscSta());
        target.put("schoolQual",scholarshipX.getSchoolQual());
        target.put("schoolUnQual",scholarshipX.getSchoolUnQual());
        target.put("cscQual",scholarshipX.getCscQual());
        target.put("cscUnQual",scholarshipX.getCscUnQual());

        return target;
    }
}
