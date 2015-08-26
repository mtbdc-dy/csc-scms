package gov.gwssi.csc.scms.domain.student;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.warning.Warning;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SCMS_STUDENT")
public class Student implements Cloneable {
    /**
     * STUDENT主键ID
     */
    @Id
    private String id;
    /**
     * CscID
     */
    @Column(name = "CscId", length = 12, unique = true, nullable = true)
    private String cscId;
    /**
     * 基本信息
     */
    @OneToOne
    @JoinColumn(name = "BasicInfo", unique = true, nullable = false)
    private BasicInfo basicInfo;
    /**
     * 来华前概况
     */
    @OneToOne
    @JoinColumn(name = "ProfilesHistory")
    private ProfilesHistory profilesHistory;
    /**
     * 注册信息
     */
    @OneToOne
    @JoinColumn(name = "RegistrationInfo", unique = true)
    private RegistrationInfo registrationInfo;
    /**
     * 商议信息
     */
    @OneToOne
    @JoinColumn(name = "Discuss", unique = true)
    private Discuss discuss;
    /**
     * 学籍信息
     */
    @OneToOne
    @JoinColumn(name = "SchoolRoll", unique = true)
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

    /**
     * 异动记录
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Abnormal> abnormals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Ticket> tickets;

    public Warning getWarning() {
        return warning;
    }

    public void setWarning(Warning warning) {
        this.warning = warning;
    }
    /**
     * 预警名单
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "warning", unique = true)
    private Warning warning;

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

    public List<Abnormal> getAbnormals() {
        return abnormals;
    }

    public void setAbnormals(List<Abnormal> abnormals) {
        this.abnormals = abnormals;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Student clone() {
        try {
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new Student();
    }
}
