package gov.gwssi.csc.scms.domain.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.warning.Warning;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BasicInfo", unique = true, nullable = false)
    private BasicInfo basicInfo;
    /**
     * 来华前概况
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProfilesHistory")
    private ProfilesHistory profilesHistory;
    /**
     * 注册信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RegistrationInfo", unique = true)
    private RegistrationInfo registrationInfo;
    /**
     * 商议信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Discuss", unique = true)
    private Discuss discuss;
    /**
     * 学籍信息
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SchoolRoll", unique = true)
    private SchoolRoll schoolRoll;
    /**
     * 相关地址
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
    /**
     * 突发事件
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Accident> accidents = new ArrayList<Accident>();
    /**
     * 校友信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOLFELLOW")
    private Schoolfellow schoolfellow;
    /**
     * 成绩信息
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Grade> Grades = new ArrayList<Grade>();
    /**
     * 成绩附件
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<GradeAttachment> gradeAttachment;

    /**
     * 异动记录
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Abnormal> abnormals;

    /**
     * 机票信息
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Ticket> tickets;

    /**
     * 保险信息
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Insurance> insurances;

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    /**
     * 奖学金信息
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<ScholarshipX> scholarshipXs;

    public List<ScholarshipX> getScholarshipXs() {
        return scholarshipXs;
    }

    public void setScholarshipXs(List<ScholarshipX> scholarshipXs) {
        this.scholarshipXs = scholarshipXs;
    }

    /**
     * 预警名单
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "warning", unique = true)
    private Warning warning;

    public Warning getWarning() {
        return warning;
    }

    public void setWarning(Warning warning) {
        this.warning = warning;
    }


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
    }

    public ProfilesHistory getProfilesHistory() {
        return profilesHistory;
    }

    public void setProfilesHistory(ProfilesHistory profilesHistory) {
        this.profilesHistory = profilesHistory;
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
    }

    public SchoolRoll getSchoolRoll() {
        return schoolRoll;
    }

    public void setSchoolRoll(SchoolRoll schoolRoll) {
        this.schoolRoll = schoolRoll;
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

    @JsonIgnore
    public Student getStudent() throws CloneNotSupportedException {
        ProfilesHistory profilesHistory = this.getProfilesHistory() != null ? this.getProfilesHistory().clone() : null;
        BasicInfo basicInfo = this.getBasicInfo() != null ? this.getBasicInfo().clone() : null;
        RegistrationInfo registrationInfo = this.getRegistrationInfo() != null ? this.getRegistrationInfo().clone() : null;
        Discuss discuss = this.getDiscuss() != null ? this.getDiscuss().clone() : null;
        SchoolRoll schoolRoll = this.getSchoolRoll() != null ? this.getSchoolRoll().clone() : null;
        List<RelatedAddress> relatedAddresses = this.getRelatedAddress();
        List<Accident> accidents = this.getAccidents();
        Schoolfellow schoolfellow = this.getSchoolfellow() != null ? this.getSchoolfellow().clone() : null;
        List<Grade> grades = this.getGrades();
        List<GradeAttachment> gradeAttachments = this.getGradeAttachment();
        List<Abnormal> abnormals = this.getAbnormals();
        List<Ticket> tickets = this.getTickets();
        List<Insurance> insurances = this.getInsurances();
        List<ScholarshipX> scholarshipXList = this.getScholarshipXs();
        Warning warning = this.getWarning() != null ? this.getWarning().clone() : null;

        Student student = new Student();
        student.setId(this.id);
        student.setCscId(this.cscId);
        student.setProfilesHistory(profilesHistory);
        student.setBasicInfo(basicInfo);
        student.setRegistrationInfo(registrationInfo);
        student.setDiscuss(discuss);
        student.setSchoolRoll(schoolRoll);
        student.setRelatedAddress(relatedAddresses);
        student.setAccidents(accidents);
        student.setSchoolfellow(schoolfellow);
        student.setGrades(grades);
        student.setGradeAttachment(gradeAttachments);
        student.setAbnormals(abnormals);
        student.setTickets(tickets);
        student.setInsurances(insurances);
        student.setScholarshipXs(scholarshipXList);
        student.setWarning(warning);
        return student;
    }


}
