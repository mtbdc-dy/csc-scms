package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.GradeAttachment;
import gov.gwssi.csc.scms.repository.student.GradeAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("gradeAttachmentService")
public class GradeAttachmentService {

    @Autowired
    private GradeAttachmentRepository gradeAttachmentRepository;

    public GradeAttachment saveGradeAttachment(GradeAttachment gradeAttachment) {
        return gradeAttachmentRepository.save(gradeAttachment);
    }

    public Iterable saveGradeAttachment(List<GradeAttachment> gradeAttachment) {
        return gradeAttachmentRepository.save(gradeAttachment);
    }

    public GradeAttachment updateGradeAttachment(GradeAttachment gradeAttachment) {
        return gradeAttachmentRepository.save(gradeAttachment);
    }
}
