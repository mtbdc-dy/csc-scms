package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SCMS_STUDENT")
public class Student {
    /**
     * STUDENT主键ID
     */
    @Id
    private String id;
    /**
     * CSCID
     */
    @Column(name = "CSCID", length = 12, unique = true, nullable = true)
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
    /**
     * 突发事件
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<Accident> accidents = new ArrayList<Accident>();
    /**
     * 校友信息
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SCHOOLFELLOW")
    private Schoolfellow schoolfellow;
    /**
     * 成绩信息
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<Grade> Grades = new ArrayList<Grade>();
    /**
     * 成绩附件
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<GradeAttachment> gradeAttachment;

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

    public Schoolfellow getSchoolfellow() {
        return schoolfellow;
    }

    public void setSchoolfellow(Schoolfellow schoolfellow) {
        this.schoolfellow = schoolfellow;
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
