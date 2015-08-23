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

    public GradeAttachment getGradeAttachmentById(String id) {
        return gradeAttachmentRepository.findOne(id);
    }

    public List<GradeAttachment> saveGradeAttachment(List<GradeAttachment> gradeAttachments) {
        for (GradeAttachment gradeAttachment : gradeAttachments)
            gradeAttachment.setId(getBaseDao().getIdBySequence("SEQ_GRADEATTACHMENT"));
        return (List<GradeAttachment>) gradeAttachmentRepository.save(gradeAttachments);
    }

    public GradeAttachment saveGradeAttachment(GradeAttachment gradeAttachment) {
        gradeAttachment.setId(getBaseDao().getIdBySequence("SEQ_GRADE"));
        return gradeAttachmentRepository.save(gradeAttachment);
    }

    public GradeAttachment updateGradeAttachment(GradeAttachment gradeAttachment) {
        gradeAttachment.setStudent(getGradeAttachmentById(gradeAttachment.getId()).getStudent());
        return gradeAttachmentRepository.save(gradeAttachment);
    }

    public List<GradeAttachment> updateGradeAttachment(List<GradeAttachment> gradeAttachments) {
        List<GradeAttachment> list = new ArrayList<GradeAttachment>();
        for (GradeAttachment gradeAttachment : gradeAttachments)
            list.add(updateGradeAttachment(gradeAttachment));
        return list;
    }

    public List<GradeAttachment> getGradeAttachmentByStudentId(String studentId) {
        List<GradeAttachment> gradeAttachments = gradeAttachmentRepository.findByStudentId(studentId);
        for(int i=0;i<gradeAttachments.size();i++){
            gradeAttachments.get(i).setStudent(null);
        }
        return gradeAttachments;
    }
}
