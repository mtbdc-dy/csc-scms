package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**学籍信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_SCHOOLROLL")
public class SchoolRoll {
    @Id private Long id;
    @OneToOne
    private Student student;
    private String certificate_type;//证件种类
    private String register_state;//报到状态 0未处理 1报到 2放弃来华
    private String disciplines;//学科门类
    private String subject;//一级学科
    private String major;//专业
    private String teach_language;//授课语言
    private String stu_type;//学生类别
    private String colleague;//院系名称
    private Date arrival_time;//来华时间
    private String school_roll_state;//学籍状态
    private String current_university;//当前院校
    private Date plan_leave_time ;//预计离华时间
    private Long scholarship_review_year;//奖学金评审年度
    private String scholarship_review_opinion;//奖学金处理意见
    private String degree_certificate_NO;//学位证书编号
    private String certificate_NO;//证件号码
    private String appropriations;//经费办法
    private String chn_frm_province;//汉补省市
    private String chn_frm_university;//汉补院校
    private Date chn_frm_start_time;//汉补开始时间
    private Date chn_frm_end_time;//汉补结束时间
    private String major_province;//专业学习省市
    private String major_university;//专业学习院校
    private String register_NO;//学籍电子注册号
    private Date major_start_time;//入专业院校时间
    private Boolean leave_china;//是否离华
    private Date leave_time;//离华时间
    private String leave_reason;//离华原因
    private String scholarship_review_result;//奖学金评审结果
    private String academic_certificate_NO;//学历证书编号
    private String abnormal_document_NO;//异动发文编号
    private String 在学记录; //有问题
    private String remark;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(String certificate_type) {
        this.certificate_type = certificate_type;
    }

    public String getRegister_state() {
        return register_state;
    }

    public void setRegister_state(String register_state) {
        this.register_state = register_state;
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

    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
    }

    public String getStu_type() {
        return stu_type;
    }

    public void setStu_type(String stu_type) {
        this.stu_type = stu_type;
    }

    public String getColleague() {
        return colleague;
    }

    public void setColleague(String colleague) {
        this.colleague = colleague;
    }

    public Date getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getSchool_roll_state() {
        return school_roll_state;
    }

    public void setSchool_roll_state(String school_roll_state) {
        this.school_roll_state = school_roll_state;
    }

    public String getCurrent_university() {
        return current_university;
    }

    public void setCurrent_university(String current_university) {
        this.current_university = current_university;
    }

    public Date getPlan_leave_time() {
        return plan_leave_time;
    }

    public void setPlan_leave_time(Date plan_leave_time) {
        this.plan_leave_time = plan_leave_time;
    }

    public Long getScholarship_review_year() {
        return scholarship_review_year;
    }

    public void setScholarship_review_year(Long scholarship_review_year) {
        this.scholarship_review_year = scholarship_review_year;
    }

    public String getScholarship_review_opinion() {
        return scholarship_review_opinion;
    }

    public void setScholarship_review_opinion(String scholarship_review_opinion) {
        this.scholarship_review_opinion = scholarship_review_opinion;
    }

    public String getDegree_certificate_NO() {
        return degree_certificate_NO;
    }

    public void setDegree_certificate_NO(String degree_certificate_NO) {
        this.degree_certificate_NO = degree_certificate_NO;
    }

    public String getCertificate_NO() {
        return certificate_NO;
    }

    public void setCertificate_NO(String certificate_NO) {
        this.certificate_NO = certificate_NO;
    }

    public String getAppropriations() {
        return appropriations;
    }

    public void setAppropriations(String appropriations) {
        this.appropriations = appropriations;
    }

    public String getChn_frm_province() {
        return chn_frm_province;
    }

    public void setChn_frm_province(String chn_frm_province) {
        this.chn_frm_province = chn_frm_province;
    }

    public String getChn_frm_university() {
        return chn_frm_university;
    }

    public void setChn_frm_university(String chn_frm_university) {
        this.chn_frm_university = chn_frm_university;
    }

    public Date getChn_frm_start_time() {
        return chn_frm_start_time;
    }

    public void setChn_frm_start_time(Date chn_frm_start_time) {
        this.chn_frm_start_time = chn_frm_start_time;
    }

    public Date getChn_frm_end_time() {
        return chn_frm_end_time;
    }

    public void setChn_frm_end_time(Date chn_frm_end_time) {
        this.chn_frm_end_time = chn_frm_end_time;
    }

    public String getMajor_province() {
        return major_province;
    }

    public void setMajor_province(String major_province) {
        this.major_province = major_province;
    }

    public String getMajor_university() {
        return major_university;
    }

    public void setMajor_university(String major_university) {
        this.major_university = major_university;
    }

    public String getRegister_NO() {
        return register_NO;
    }

    public void setRegister_NO(String register_NO) {
        this.register_NO = register_NO;
    }

    public Date getMajor_start_time() {
        return major_start_time;
    }

    public void setMajor_start_time(Date major_start_time) {
        this.major_start_time = major_start_time;
    }

    public Boolean isLeave_china() {
        return leave_china;
    }

    public void setLeave_china(Boolean leave_china) {
        this.leave_china = leave_china;
    }

    public Date getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Date leave_time) {
        this.leave_time = leave_time;
    }

    public String getLeave_reason() {
        return leave_reason;
    }

    public void setLeave_reason(String leave_reason) {
        this.leave_reason = leave_reason;
    }

    public String getScholarship_review_result() {
        return scholarship_review_result;
    }

    public void setScholarship_review_result(String scholarship_review_result) {
        this.scholarship_review_result = scholarship_review_result;
    }

    public String getAcademic_certificate_NO() {
        return academic_certificate_NO;
    }

    public void setAcademic_certificate_NO(String academic_certificate_NO) {
        this.academic_certificate_NO = academic_certificate_NO;
    }

    public String getAbnormal_document_NO() {
        return abnormal_document_NO;
    }

    public void setAbnormal_document_NO(String abnormal_document_NO) {
        this.abnormal_document_NO = abnormal_document_NO;
    }

    public String get在学记录() {
        return 在学记录;
    }

    public void set在学记录(String 在学记录) {
        this.在学记录 = 在学记录;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
