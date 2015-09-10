package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipJ;
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
public class ScholarshipJConverter extends BaseService implements Converter<ScholarshipJ, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(ScholarshipJ scholarshipJ) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("ID",scholarshipJ.getId());
        target.put("SCHOLARSHIPID",scholarshipJ.getScholarshipId());
        target.put("YEAR",scholarshipJ.getYear());
        target.put("STATE",scholarshipJ.getState());
        target.put("PROVINCE",scholarshipJ.getProvince());
        target.put("UNIV",scholarshipJ.getUniv());
        target.put("SCHOOLDATE",scholarshipJ.getSchooldate());
        target.put("QUALIFIED",scholarshipJ.getQualified());
        target.put("COUNT",scholarshipJ.getCount());
        target.put("SCHOOLQUAl",scholarshipJ.getSchoolQual());
        target.put("SCHOOLUNQUAl",scholarshipJ.getSchoolUnQual());
        target.put("CSCQUAL",scholarshipJ.getCscQual());
        target.put("CSCUNQUAL",scholarshipJ.getCscUnQual());
        target.put("CSCDATE",scholarshipJ.getCscdate());

        return target;
    }
}
