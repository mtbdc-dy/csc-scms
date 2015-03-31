package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 来华前概况、申请信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name="SCMS_REGISTRATION_INFO")
public class RegistrationInfo {
    @Id private Long id;
    private String f_specialty;
    private String disciplines;
    private String c_provinces;
    private String occupation;
    private String remark;
    private String educated;
    private String start_study_time;
    private String teach_language;
    private String update_by;
    private String jxjlb;
    private String chinese_study;
    private String parent_language;
    private String a_university;
    private String specialty;
    private String english_teach;
    private String b_university;
    private String b_provinces;
    private String a_provinces;
    private String work;
    private java.sql.Date update_date;
    private String end_study_time;
    private String english_level;
    private String stu_type;
    private String chinese_level;
    private String c_university;
    private String health_checkup;
    private String fund;

    @OneToOne
    private Student student;

    public String getF_specialty() {
        return f_specialty;
    }

    public void setF_specialty(String f_specialty) {
        this.f_specialty = f_specialty;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getC_provinces() {
        return c_provinces;
    }

    public void setC_provinces(String c_provinces) {
        this.c_provinces = c_provinces;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEducated() {
        return educated;
    }

    public void setEducated(String educated) {
        this.educated = educated;
    }

    public String getStart_study_time() {
        return start_study_time;
    }

    public void setStart_study_time(String start_study_time) {
        this.start_study_time = start_study_time;
    }

    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getJxjlb() {
        return jxjlb;
    }

    public void setJxjlb(String jxjlb) {
        this.jxjlb = jxjlb;
    }

    public String getChinese_study() {
        return chinese_study;
    }

    public void setChinese_study(String chinese_study) {
        this.chinese_study = chinese_study;
    }

    public String getParent_language() {
        return parent_language;
    }

    public void setParent_language(String parent_language) {
        this.parent_language = parent_language;
    }

    public String getA_university() {
        return a_university;
    }

    public void setA_university(String a_university) {
        this.a_university = a_university;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEnglish_teach() {
        return english_teach;
    }

    public void setEnglish_teach(String english_teach) {
        this.english_teach = english_teach;
    }

    public String getB_university() {
        return b_university;
    }

    public void setB_university(String b_university) {
        this.b_university = b_university;
    }

    public String getB_provinces() {
        return b_provinces;
    }

    public void setB_provinces(String b_provinces) {
        this.b_provinces = b_provinces;
    }

    public String getA_provinces() {
        return a_provinces;
    }

    public void setA_provinces(String a_provinces) {
        this.a_provinces = a_provinces;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public java.sql.Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(java.sql.Date update_date) {
        this.update_date = update_date;
    }

    public String getEnd_study_time() {
        return end_study_time;
    }

    public void setEnd_study_time(String end_study_time) {
        this.end_study_time = end_study_time;
    }

    public String getEnglish_level() {
        return english_level;
    }

    public void setEnglish_level(String english_level) {
        this.english_level = english_level;
    }

    public String getStu_type() {
        return stu_type;
    }

    public void setStu_type(String stu_type) {
        this.stu_type = stu_type;
    }

    public String getChinese_level() {
        return chinese_level;
    }

    public void setChinese_level(String chinese_level) {
        this.chinese_level = chinese_level;
    }

    public String getC_university() {
        return c_university;
    }

    public void setC_university(String c_university) {
        this.c_university = c_university;
    }

    public String getHealth_checkup() {
        return health_checkup;
    }

    public void setHealth_checkup(String health_checkup) {
        this.health_checkup = health_checkup;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
