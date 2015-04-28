package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 来华前概况
 */
@Entity
@Table(name = "SCMS_PROFILES_HISTORY")
public class ProfilesHistory {
    @Id
    private String id;
    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 职业
     */
    private String occupation;
    /**
     * 母语
     */
    private String nativeLanguage;
    /**
     * 体检
     */
    private Boolean phyExam;
    /**
     * 汉语水平
     */
    private String chnLevel;
    /**
     * 英语水平
     */
    private String engLevel;
    /**
     * 学历
     */
    private String educated;
    /**
     * 可否英语授课
     */
    private Boolean engTeach;
    /**
     * 学生
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public Boolean isPhyExam() {
        return phyExam;
    }

    public void setPhyExam(Boolean phyExam) {
        this.phyExam = phyExam;
    }

    public String getChnLevel() {
        return chnLevel;
    }

    public void setChnLevel(String chnLevel) {
        this.chnLevel = chnLevel;
    }

    public String getEngLevel() {
        return engLevel;
    }

    public void setEngLevel(String engLevel) {
        this.engLevel = engLevel;
    }

    public String getEducated() {
        return educated;
    }

    public void setEducated(String educated) {
        this.educated = educated;
    }

    public Boolean isEngTeach() {
        return engTeach;
    }

    public void setEngTeach(Boolean engTeach) {
        this.engTeach = engTeach;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
