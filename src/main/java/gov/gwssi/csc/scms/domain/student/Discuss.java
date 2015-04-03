package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**
 * 商议信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_DISCUSS")
public class Discuss {
    @Id
    private Long id;
    @OneToOne
    private Student student;
    private String appropriations;//经费办法
    private String stu_type;//学生类别
    private String teach_language;//授课语言
    private String disciplines;//学科门类
    private String subject;//一级学科
    private String major;//专业
    private Boolean chn_frm_needed;//是否汉补
    private Date chn_frm_start_time;//汉补开始时间
    private Date chn_frm_end_time;//汉补结束时间
    private String province_1;//省市1
    private String province_2;//省市2
    private String province_3;//省市3
    private String university_1;//院校1
    private String university_2;//院校2
    private String university_3;//院校3
    private String matriculate;//商议状态 1表 2录 3否 4待 5发 6备 7单报
    private String handle_status;//经办情况
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

    public String getAppropriations() {
        return appropriations;
    }

    public void setAppropriations(String appropriations) {
        this.appropriations = appropriations;
    }

    public String getStu_type() {
        return stu_type;
    }

    public void setStu_type(String stu_type) {
        this.stu_type = stu_type;
    }

    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
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

    public Boolean isChn_frm_needed() {
        return chn_frm_needed;
    }

    public void setChn_frm_needed(Boolean chn_frm_needed) {
        this.chn_frm_needed = chn_frm_needed;
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

    public String getProvince_1() {
        return province_1;
    }

    public void setProvince_1(String province_1) {
        this.province_1 = province_1;
    }

    public String getProvince_2() {
        return province_2;
    }

    public void setProvince_2(String province_2) {
        this.province_2 = province_2;
    }

    public String getProvince_3() {
        return province_3;
    }

    public void setProvince_3(String province_3) {
        this.province_3 = province_3;
    }

    public String getUniversity_1() {
        return university_1;
    }

    public void setUniversity_1(String university_1) {
        this.university_1 = university_1;
    }

    public String getUniversity_2() {
        return university_2;
    }

    public void setUniversity_2(String university_2) {
        this.university_2 = university_2;
    }

    public String getUniversity_3() {
        return university_3;
    }

    public void setUniversity_3(String university_3) {
        this.university_3 = university_3;
    }

    public String getMatriculate() {
        return matriculate;
    }

    public void setMatriculate(String matriculate) {
        this.matriculate = matriculate;
    }

    public String getHandle_status() {
        return handle_status;
    }

    public void setHandle_status(String handle_status) {
        this.handle_status = handle_status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
