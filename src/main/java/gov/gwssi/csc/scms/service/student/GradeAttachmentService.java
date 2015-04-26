package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.GradeAttachment;
import gov.gwssi.csc.scms.repository.student.GradeAttachmentRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("gradeAttachmentService")
public class GradeAttachmentService extends BaseService {

    @Autowired
    @Qualifier("gradeAttachmentRepository")
    private GradeAttachmentRepository gradeAttachmentRepository;

    public GradeAttachment getGradeAttachmentById(Long id) {
        return gradeAttachmentRepository.findOne(id);
    }

    public GradeAttachment saveGradeAttachment(GradeAttachment gradeAttachment) {
        return gradeAttachmentRepository.save(gradeAttachment);
    }

    public Iterable saveGradeAttachment(List<GradeAttachment> gradeAttachment) {
        return gradeAttachmentRepository.save(gradeAttachment);
    }

    public GradeAttachment updateGradeAttachment(GradeAttachment gradeAttachment) {
        GradeAttachment gradeAttachment1 = getGradeAttachmentById(gradeAttachment.getId());
        super.copyFiledValue(GradeAttachment.class, gradeAttachment, gradeAttachment1);
        return saveGradeAttachment(gradeAttachment1);
    }

    public List<GradeAttachment> updateGradeAttachment(List<GradeAttachment> gradeAttachments) {
        List<GradeAttachment> list = new ArrayList<GradeAttachment>();
        for (GradeAttachment gradeAttachment : gradeAttachments)
            list.add(updateGradeAttachment(gradeAttachment));
        return list;
    }

    public List<GradeAttachment> getGradeAttachmentByStudentId(Long studentId) {
        return gradeAttachmentRepository.findByStudentId(studentId);
    }
}
