package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_SCHOOLROLL")
public class Schoolroll {
    @Id
    private Long id;
    private String hb_start_time;
    private String csc_id;
    private String scholarship_end_time;
    private String is_leave_china;
    private String paperwork_type;
    private String zyyx_enter_time;
    private String hb_provinces;
    private String schoolroll_state;
    private String disciplines;
    private String scholarship_recovery_time;
    private String hb_university;
    private String jxjqkjscholarship_recordl;
    private String hb_end_time;
    private String fund_criteria;
    private String specialty;
    private String scholarship_start_time;
    private String scholarship_program;
    private String scholarship_suspend_time;
    private String current_state;
    private String to_school_time;
    private String current_university;
    private String stu_type;
    private String leave_school_time;
    private String file_number;
    private String update_by;
    private String to_china_time;
    private String china_leave_time;
    private String remark;
    private String leave_reason;
    private String f_specialty;
    private Date update_date;
    private String specialty_provinces;
    private String fund;
    private String performance;
    private String is_register;
    private String paperwork_number;
    private String teach_language;
    private String specialty_university;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHb_start_time() {
        return hb_start_time;
    }

    public void setHb_start_time(String hb_start_time) {
        this.hb_start_time = hb_start_time;
    }

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public String getScholarship_end_time() {
        return scholarship_end_time;
    }

    public void setScholarship_end_time(String scholarship_end_time) {
        this.scholarship_end_time = scholarship_end_time;
    }

    public String getIs_leave_china() {
        return is_leave_china;
    }

    public void setIs_leave_china(String is_leave_china) {
        this.is_leave_china = is_leave_china;
    }

    public String getPaperwork_type() {
        return paperwork_type;
    }

    public void setPaperwork_type(String paperwork_type) {
        this.paperwork_type = paperwork_type;
    }

    public String getZyyx_enter_time() {
        return zyyx_enter_time;
    }

    public void setZyyx_enter_time(String zyyx_enter_time) {
        this.zyyx_enter_time = zyyx_enter_time;
    }

    public String getHb_provinces() {
        return hb_provinces;
    }

    public void setHb_provinces(String hb_provinces) {
        this.hb_provinces = hb_provinces;
    }

    public String getSchoolroll_state() {
        return schoolroll_state;
    }

    public void setSchoolroll_state(String schoolroll_state) {
        this.schoolroll_state = schoolroll_state;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getScholarship_recovery_time() {
        return scholarship_recovery_time;
    }

    public void setScholarship_recovery_time(String scholarship_recovery_time) {
        this.scholarship_recovery_time = scholarship_recovery_time;
    }

    public String getHb_university() {
        return hb_university;
    }

    public void setHb_university(String hb_university) {
        this.hb_university = hb_university;
    }

    public String getJxjqkjscholarship_recordl() {
        return jxjqkjscholarship_recordl;
    }

    public void setJxjqkjscholarship_recordl(String jxjqkjscholarship_recordl) {
        this.jxjqkjscholarship_recordl = jxjqkjscholarship_recordl;
    }

    public String getHb_end_time() {
        return hb_end_time;
    }

    public void setHb_end_time(String hb_end_time) {
        this.hb_end_time = hb_end_time;
    }

    public String getFund_criteria() {
        return fund_criteria;
    }

    public void setFund_criteria(String fund_criteria) {
        this.fund_criteria = fund_criteria;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getScholarship_start_time() {
        return scholarship_start_time;
    }

    public void setScholarship_start_time(String scholarship_start_time) {
        this.scholarship_start_time = scholarship_start_time;
    }

    public String getScholarship_program() {
        return scholarship_program;
    }

    public void setScholarship_program(String scholarship_program) {
        this.scholarship_program = scholarship_program;
    }

    public String getScholarship_suspend_time() {
        return scholarship_suspend_time;
    }

    public void setScholarship_suspend_time(String scholarship_suspend_time) {
        this.scholarship_suspend_time = scholarship_suspend_time;
    }

    public String getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state(String current_state) {
        this.current_state = current_state;
    }

    public String getTo_school_time() {
        return to_school_time;
    }

    public void setTo_school_time(String to_school_time) {
        this.to_school_time = to_school_time;
    }

    public String getCurrent_university() {
        return current_university;
    }

    public void setCurrent_university(String current_university) {
        this.current_university = current_university;
    }

    public String getStu_type() {
        return stu_type;
    }

    public void setStu_type(String stu_type) {
        this.stu_type = stu_type;
    }

    public String getLeave_school_time() {
        return leave_school_time;
    }

    public void setLeave_school_time(String leave_school_time) {
        this.leave_school_time = leave_school_time;
    }

    public String getFile_number() {
        return file_number;
    }

    public void setFile_number(String file_number) {
        this.file_number = file_number;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getTo_china_time() {
        return to_china_time;
    }

    public void setTo_china_time(String to_china_time) {
        this.to_china_time = to_china_time;
    }

    public String getChina_leave_time() {
        return china_leave_time;
    }

    public void setChina_leave_time(String china_leave_time) {
        this.china_leave_time = china_leave_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLeave_reason() {
        return leave_reason;
    }

    public void setLeave_reason(String leave_reason) {
        this.leave_reason = leave_reason;
    }

    public String getF_specialty() {
        return f_specialty;
    }

    public void setF_specialty(String f_specialty) {
        this.f_specialty = f_specialty;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getSpecialty_provinces() {
        return specialty_provinces;
    }

    public void setSpecialty_provinces(String specialty_provinces) {
        this.specialty_provinces = specialty_provinces;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getIs_register() {
        return is_register;
    }

    public void setIs_register(String is_register) {
        this.is_register = is_register;
    }

    public String getPaperwork_number() {
        return paperwork_number;
    }

    public void setPaperwork_number(String paperwork_number) {
        this.paperwork_number = paperwork_number;
    }

    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
    }

    public String getSpecialty_university() {
        return specialty_university;
    }

    public void setSpecialty_university(String specialty_university) {
        this.specialty_university = specialty_university;
    }
}
