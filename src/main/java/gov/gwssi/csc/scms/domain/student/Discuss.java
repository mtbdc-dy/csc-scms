package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**商议信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "LHLX_DISCUSS")
public class Discuss {
    @Id private Long id;
    private String specialty;
    private String remark;
    private String is_chn_frm_needed;
    private String chn_frm_start_time;
    private String stu_type;
    private String chn_frm_end_time;
    private String specialty_end_time;
    private String a_provinces;
    private String fund;
    private String handle_situation;
    private Date update_date;
    private String study_type;
    private String matriculate;
    private String c_provinces;
    private String disciplines;
    private String b_provinces;
    private String b_university;
    private String f_specialty;
    private String c_university;
    private String teach_language;
    private String a_university;
    private String update_by;
    private String specialty_start_time;

    @OneToOne//(mappedBy = "student")
//    @JoinColumn(name="csc_id",insertable=true,unique=true)
    private Student student;

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIs_chn_frm_needed() {
        return is_chn_frm_needed;
    }

    public void setIs_chn_frm_needed(String is_chn_frm_needed) {
        this.is_chn_frm_needed = is_chn_frm_needed;
    }

    public String getChn_frm_start_time() {
        return chn_frm_start_time;
    }

    public void setChn_frm_start_time(String chn_frm_start_time) {
        this.chn_frm_start_time = chn_frm_start_time;
    }

    public String getStu_type() {
        return stu_type;
    }

    public void setStu_type(String stu_type) {
        this.stu_type = stu_type;
    }

    public String getChn_frm_end_time() {
        return chn_frm_end_time;
    }

    public void setChn_frm_end_time(String chn_frm_end_time) {
        this.chn_frm_end_time = chn_frm_end_time;
    }

    public String getSpecialty_end_time() {
        return specialty_end_time;
    }

    public void setSpecialty_end_time(String specialty_end_time) {
        this.specialty_end_time = specialty_end_time;
    }

    public String getA_provinces() {
        return a_provinces;
    }

    public void setA_provinces(String a_provinces) {
        this.a_provinces = a_provinces;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getHandle_situation() {
        return handle_situation;
    }

    public void setHandle_situation(String handle_situation) {
        this.handle_situation = handle_situation;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getStudy_type() {
        return study_type;
    }

    public void setStudy_type(String study_type) {
        this.study_type = study_type;
    }

    public String getMatriculate() {
        return matriculate;
    }

    public void setMatriculate(String matriculate) {
        this.matriculate = matriculate;
    }

    public String getC_provinces() {
        return c_provinces;
    }

    public void setC_provinces(String c_provinces) {
        this.c_provinces = c_provinces;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getB_provinces() {
        return b_provinces;
    }

    public void setB_provinces(String b_provinces) {
        this.b_provinces = b_provinces;
    }

    public String getB_university() {
        return b_university;
    }

    public void setB_university(String b_university) {
        this.b_university = b_university;
    }

    public String getF_specialty() {
        return f_specialty;
    }

    public void setF_specialty(String f_specialty) {
        this.f_specialty = f_specialty;
    }

    public String getC_university() {
        return c_university;
    }

    public void setC_university(String c_university) {
        this.c_university = c_university;
    }

    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
    }

    public String getA_university() {
        return a_university;
    }

    public void setA_university(String a_university) {
        this.a_university = a_university;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getSpecialty_start_time() {
        return specialty_start_time;
    }

    public void setSpecialty_start_time(String specialty_start_time) {
        this.specialty_start_time = specialty_start_time;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
