package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_DISCUSS")
public class Discuss {
    @Id
    private Long id;
    private String specialty;
    private String csc_id;
    private String remark;
    private String is_hb;
    private String hb_start_time;
    private String stu_type;
    private String hb_end_time;
    private String specialty_end_time;
    private String a_provinces;
    private String fund;
    private String jbqk;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIs_hb() {
        return is_hb;
    }

    public void setIs_hb(String is_hb) {
        this.is_hb = is_hb;
    }

    public String getHb_start_time() {
        return hb_start_time;
    }

    public void setHb_start_time(String hb_start_time) {
        this.hb_start_time = hb_start_time;
    }

    public String getStu_type() {
        return stu_type;
    }

    public void setStu_type(String stu_type) {
        this.stu_type = stu_type;
    }

    public String getHb_end_time() {
        return hb_end_time;
    }

    public void setHb_end_time(String hb_end_time) {
        this.hb_end_time = hb_end_time;
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

    public String getJbqk() {
        return jbqk;
    }

    public void setJbqk(String jbqk) {
        this.jbqk = jbqk;
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
}
