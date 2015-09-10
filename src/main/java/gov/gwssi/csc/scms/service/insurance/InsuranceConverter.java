package gov.gwssi.csc.scms.service.insurance;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
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
public class InsuranceConverter extends BaseService implements Converter<Insurance, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(Insurance insurance) {
        Map<String, Object> target = new HashMap<String, Object>();
        Student student = insurance.getStudent();
        BasicInfo basicInfo = student.getBasicInfo();
        SchoolRoll schoolRoll = student.getSchoolRoll();
        target.put("id",insurance.getId());
        target.put("studentId",student.getId());
        target.put("cscId",student.getCscId());
        target.put("gender",basicInfo.getGender());
        target.put("passportName",basicInfo.getPassportName());
        target.put("country",basicInfo.getCountry());
        target.put("birthday",basicInfo.getBirthday());
        target.put("arrivalDate",schoolRoll.getArrivalDate());
        target.put("planLeaveDate",schoolRoll.getPlanLeaveDate());
        target.put("appropriation",schoolRoll.getAppropriation());
        target.put("currentUniversity",schoolRoll.getCurrentUniversity());
        target.put("insurNo",insurance.getInsurNo());
        target.put("preSta",insurance.getPreSta());


        return target;
    }
}
