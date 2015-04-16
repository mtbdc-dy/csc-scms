package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SCMS_SCHOOLROLL")
public class SchoolRoll {
    @Id
    @SequenceGenerator(name = "SCMS_SCHOOLROLL_ID", sequenceName = "SCMS_SCHOOLROLL_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "SCMS_SCHOOLROLL_ID", strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * 证件种类
     */
    private String certificateType;
    /**
     * 是否报道
     */
    private Boolean registed;
    /**
     * 学科门类
     */
    private String disciplines;
    /**
     * 一级学科
     */
    private String subject;
    /**
     * 专业
     */
    private String major;
    /**
     * 授课语言
     */
    private String teachLanguage;
    /**
     * 学生类别
     */
    private String studentType;
    /**
     * 院系名称
     */
    private String colleague;
    /**
     * 来华时间
     */
    private Date arrivalDate;
    /**
     * 学籍状态
     */
    private String state;
    /**
     * 当前院校
     */
    private String currentUniversity;
    /**
     * 预计离华时间
     */
    private Date planLeaveDate;
    /**
     * 奖学金评审年度
     */
    private Long scholarshipYear;
    /**
     * 奖学金处理意见
     */
    private String scholarshipOpinion;
    /**
     * 学位证编号
     */
    private String degreeNO;
    /**
     * 证件号码
     */
    private String certificateNO;
    /**
     * 经费办法
     */
    private String appropriations;
    /**
     * 汉补省市
     */
    private String cramProvince;
    /**
     * 汉补院校
     */
    private String cramUniversity;
    /**
     * 汉补开始时间
     */
    private Date cramDateBegin;
    /**
     * 汉补结束时间
     */
    private Date cramDateEnd;
    /**
     * 专业学习省市
     */
    private String majorProvince;
    /**
     * 专业学习院校
     */
    private String majorUniversity;
    /**
     * 学籍电子注册号
     */
    private String elcRegisteNO;
    /**
     * 入专业院校时间
     */
    private Date majorStartDate;
    /**
     * 是否离华
     */
    private Boolean leaveChina;
    /**
     * 离华时间
     */
    private Date leave_Date;
    /**
     * 离华原因
     */
    private String leaveReason;
    /**
     * 奖学金评审结果
     */
    private String scholarshipReviewResult;
    /**
     * 学历证书编号
     */
    private String academicCertificateNO;
    /**
     *异动发文编号
     */
    private String abnormalDocumentNO;
    /**
     * 在学记录
     */
    private String studyRecord;
    /**
     * 备注
     */
    private String remark;
    /**
     * 报到状态（0未处理 1报到 2放弃来华）
     */
    private String registerState;
    /**
     * 报道年度
     */
    private String registerYear;
    /**
     * 学生
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public Boolean isRegisted() {
        return registed;
    }

    public void setRegisted(Boolean registed) {
        this.registed = registed;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTeachLanguage() {
        return teachLanguage;
    }

    public void setTeachLanguage(String teachLanguage) {
        this.teachLanguage = teachLanguage;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getColleague() {
        return colleague;
    }

    public void setColleague(String colleague) {
        this.colleague = colleague;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrentUniversity() {
        return currentUniversity;
    }

    public void setCurrentUniversity(String currentUniversity) {
        this.currentUniversity = currentUniversity;
    }

    public Date getPlanLeaveDate() {
        return planLeaveDate;
    }

    public void setPlanLeaveDate(Date planLeaveDate) {
        this.planLeaveDate = planLeaveDate;
    }

    public Long getScholarshipYear() {
        return scholarshipYear;
    }

    public void setScholarshipYear(Long scholarshipYear) {
        this.scholarshipYear = scholarshipYear;
    }

    public String getScholarshipOpinion() {
        return scholarshipOpinion;
    }

    public void setScholarshipOpinion(String scholarshipOpinion) {
        this.scholarshipOpinion = scholarshipOpinion;
    }

    public String getDegreeNO() {
        return degreeNO;
    }

    public void setDegreeNO(String degreeNO) {
        this.degreeNO = degreeNO;
    }

    public String getCertificateNO() {
        return certificateNO;
    }

    public void setCertificateNO(String certificateNO) {
        this.certificateNO = certificateNO;
    }

    public String getAppropriations() {
        return appropriations;
    }

    public void setAppropriations(String appropriations) {
        this.appropriations = appropriations;
    }

    public String getCramProvince() {
        return cramProvince;
    }

    public void setCramProvince(String cramProvince) {
        this.cramProvince = cramProvince;
    }

    public String getCramUniversity() {
        return cramUniversity;
    }

    public void setCramUniversity(String cramUniversity) {
        this.cramUniversity = cramUniversity;
    }

    public Date getCramDateBegin() {
        return cramDateBegin;
    }

    public void setCramDateBegin(Date cramDateBegin) {
        this.cramDateBegin = cramDateBegin;
    }

    public Date getCramDateEnd() {
        return cramDateEnd;
    }

    public void setCramDateEnd(Date cramDateEnd) {
        this.cramDateEnd = cramDateEnd;
    }

    public String getMajorProvince() {
        return majorProvince;
    }

    public void setMajorProvince(String majorProvince) {
        this.majorProvince = majorProvince;
    }

    public String getMajorUniversity() {
        return majorUniversity;
    }

    public void setMajorUniversity(String majorUniversity) {
        this.majorUniversity = majorUniversity;
    }

    public String getElcRegisteNO() {
        return elcRegisteNO;
    }

    public void setElcRegisteNO(String elcRegisteNO) {
        this.elcRegisteNO = elcRegisteNO;
    }

    public Date getMajorStartDate() {
        return majorStartDate;
    }

    public void setMajorStartDate(Date majorStartDate) {
        this.majorStartDate = majorStartDate;
    }

    public Boolean isLeaveChina() {
        return leaveChina;
    }

    public void setLeaveChina(Boolean leaveChina) {
        this.leaveChina = leaveChina;
    }

    public Date getLeave_Date() {
        return leave_Date;
    }

    public void setLeave_Date(Date leave_Date) {
        this.leave_Date = leave_Date;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getScholarshipReviewResult() {
        return scholarshipReviewResult;
    }

    public void setScholarshipReviewResult(String scholarshipReviewResult) {
        this.scholarshipReviewResult = scholarshipReviewResult;
    }

    public String getAcademicCertificateNO() {
        return academicCertificateNO;
    }

    public void setAcademicCertificateNO(String academicCertificateNO) {
        this.academicCertificateNO = academicCertificateNO;
    }

    public String getAbnormalDocumentNO() {
        return abnormalDocumentNO;
    }

    public void setAbnormalDocumentNO(String abnormalDocumentNO) {
        this.abnormalDocumentNO = abnormalDocumentNO;
    }

    public String getStudyRecord() {
        return studyRecord;
    }

    public void setStudyRecord(String studyRecord) {
        this.studyRecord = studyRecord;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRegisterState() {
        return registerState;
    }

    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
