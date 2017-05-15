package gov.gwssi.csc.scms.repository.scholarshipX;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * 保险管理接口
 */
public interface ScholarshipDetailRepository extends CrudRepository<ScholarshipDetail, String> {
    ScholarshipDetail findById(String id);
    @Modifying
    @Query(value="update SCMS_SCHOLARSHIP_DETAIL set CSCREVIEW = Schreview,Cscresult = Schresult,Cscreason = Schreason where SCHOLARSHIPID = :scholarshipId",nativeQuery = true)
    void sendBacking(@Param(value="scholarshipId") String scholarshipId);
    @Query(value="select t.cscid from scms.scms_scholarship_detail detail,scms.scms_schoolroll s,scms.scms_student t"+
                " where detail.studentid = s.studentid and s.studentid = t.id" +
                " and detail.scholarshipid = :scholarshipId and s.currentuniversity <> :school",nativeQuery = true)
    List<String> submitChecking(@Param(value="scholarshipId") String scholarshipId,@Param(value="school") String school);
}
