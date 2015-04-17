package gov.gwssi.csc.scms.domain.student;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SCMS_STUDENT")
public class Student {
    /**
     * STUDENT主键ID
     */
    @Id
    @SequenceGenerator(name = "SCMS_STUDENT_ID", sequenceName = "SCMS_STUDENT_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "SCMS_STUDENT_ID", strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * CSCID
     */
    @Column(name = "CSCID", length = 12)
    private String cscId;
    /**
     * 基本信息
     */
    @OneToOne
    @JoinColumn(name = "BASICINFO", unique = true, nullable = false)
    private BasicInfo basicInfo;
    /**
     * 来华前概况
     */
    @OneToOne
    @JoinColumn(name = "PROFILESHISTORY")
    private ProfilesHistory profilesHistory;
    /**
     * 注册信息
     */
    @OneToOne
    @JoinColumn(name = "REGISTRATIONINFO", unique = true)
    private RegistrationInfo registrationInfo;
    /**
     * 商议信息
     */
    @OneToOne
    @JoinColumn(name = "DISCUSS", unique = true)
    private Discuss discuss;
    /**
     * 学籍信息
     */
    @OneToOne
    @JoinColumn(name = "SCHOOLROLL", unique = true)
    private SchoolRoll schoolRoll;
    /**
     * 相关地址
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<RelatedAddress> relatedAddress = new ArrayList<>();
    /**
     * 突发事件
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Accident> accidents = new ArrayList<>();
    /**
     * 校友信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOLFELLOW")
    private SchoolFellow schoolFellow;
    /**
     * 成绩信息
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Grade> Grades = new ArrayList<>();
    /**
     * 成绩附件
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<GradeAttachment> gradeAttachment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCscId() {
        return cscId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
        basicInfo.setStudent(this);
    }

    public ProfilesHistory getProfilesHistory() {
        return profilesHistory;
    }

    public void setProfilesHistory(ProfilesHistory profilesHistory) {
        this.profilesHistory = profilesHistory;
        profilesHistory.setStudent(this);
    }

    public RegistrationInfo getRegistrationInfo() {
        return registrationInfo;
    }

    public void setRegistrationInfo(RegistrationInfo registrationInfo) {
        this.registrationInfo = registrationInfo;
    }

    public Discuss getDiscuss() {
        return discuss;
    }

    public void setDiscuss(Discuss discuss) {
        this.discuss = discuss;
        discuss.setStudent(this);
    }

    public SchoolRoll getSchoolRoll() {
        return schoolRoll;
    }

    public void setSchoolRoll(SchoolRoll schoolRoll) {
        this.schoolRoll = schoolRoll;
        schoolRoll.setStudent(this);
    }

    public List<RelatedAddress> getRelatedAddress() {
        return relatedAddress;
    }

    public void setRelatedAddress(List<RelatedAddress> relatedAddress) {
        this.relatedAddress = relatedAddress;
    }

    public List<Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(List<Accident> accidents) {
        this.accidents = accidents;
    }

    public SchoolFellow getSchoolFellow() {
        return schoolFellow;
    }

    public void setSchoolFellow(SchoolFellow schoolFellow) {
        this.schoolFellow = schoolFellow;
        schoolFellow.setStudent(this);
    }

    public List<Grade> getGrades() {
        return Grades;
    }

    public void setGrades(List<Grade> grades) {
        Grades = grades;
    }

    public List<GradeAttachment> getGradeAttachment() {
        return gradeAttachment;
    }

    public void setGradeAttachment(List<GradeAttachment> gradeAttachment) {
        this.gradeAttachment = gradeAttachment;
    }
}
