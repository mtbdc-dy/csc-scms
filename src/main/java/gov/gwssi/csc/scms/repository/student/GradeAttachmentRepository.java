package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.GradeAttachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 */
@Repository(value = "gradeAttachmentRepository")
public interface GradeAttachmentRepository extends CrudRepository<GradeAttachment, String> {
    List<GradeAttachment> findByStudentId(String studentId);
}

