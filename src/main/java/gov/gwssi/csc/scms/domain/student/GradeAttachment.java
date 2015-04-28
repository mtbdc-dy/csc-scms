package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

@Entity
@Table(name = "SCMS_GRADEATTACHMENT")
public class GradeAttachment {
    @Id
    private String id;
    /**
     * cscid
     */
    private String cscId;
    /**
     * 附件地址
     */
    private String attachmentUri;
    /**
     * 学生id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCscId() {
        return cscId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public String getAttachmentUri() {
        return attachmentUri;
    }

    public void setAttachmentUri(String attachmentUri) {
        this.attachmentUri = attachmentUri;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
