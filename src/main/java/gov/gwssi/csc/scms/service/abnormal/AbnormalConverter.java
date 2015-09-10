package gov.gwssi.csc.scms.service.abnormal;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
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
public class AbnormalConverter extends BaseService implements Converter<Abnormal, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(Abnormal abnormal) {
        Map<String, Object> target = new HashMap<String, Object>();
        Student student = abnormal.getStudent();
        BasicInfo basicInfo = student.getBasicInfo();
        SchoolRoll schoolRoll = student.getSchoolRoll();
        target.put("abnormalId",abnormal.getId());
        target.put("studentId",student.getId());
        target.put("cscId",student.getCscId());
        target.put("gender",basicInfo.getGender());
        target.put("passportName",basicInfo.getPassportName());
        target.put("applyUserName",abnormal.getApplyUserName());
        target.put("applyTime",abnormal.getApplyTime());
        target.put("country",basicInfo.getCountry());
        target.put("rollState",schoolRoll.getState());
        target.put("handleState",abnormal.getState());
        target.put("reasonId",abnormal.getReasonId());
        target.put("reasonTypeId",abnormal.getReasonTypeId());
        target.put("reason",abnormal.getReason());
        target.put("applyUri",abnormal.getApplyUri());
        target.put("approResult",abnormal.getApproResult());
        target.put("approOpinion",abnormal.getApproOpinion());
        target.put("publicUri",abnormal.getPublicUri());
        target.put("applyUserId",abnormal.getApplyUserId());
        target.put("reportUserId",abnormal.getReportUserId());
        target.put("reportUserName",abnormal.getReportUserName());
        target.put("reportTime",abnormal.getReportTime());
        target.put("approUserId",abnormal.getApproUserId());
        target.put("approUserName",abnormal.getApproUserName());
        target.put("approTime",abnormal.getApproTime());
        target.put("handleUserId",abnormal.getHandleUserId());
        target.put("handleUserName",abnormal.getHandleUserName());
        target.put("handleTime",abnormal.getHandleTime());
        target.put("updateBy",abnormal.getUpdateBy());
        target.put("updated",abnormal.getUpdated());

        return target;
    }
}
