package gov.gwssi.csc.scms.domain.queryfilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生列表查询实体类
 * Created by Murray on 2015/4/2.
 */
public class StudentFilterObject implements FilterObject {

    /**
     * 主要查询条件
     */
    private String csc_id = null;//SCS登记号
    private String passport_name = null;//护照名称
    private String continent_name = null;//洲别
    private String country_name = null;//国籍
    private String project_type = null;//项目类别
    private String project_name = null;//项目名称
    private String register_state = null;//报到状态 0未处理 1报到 2放弃来华

    /**
     * 隐藏查询条件
     */
    private String planned = null;//名额性质（计划内 计划外）
    private String dispatch = null;//派遣途径
    private String travel_type = null;//国际旅费
    private String annual = null; //年度
    private String stu_type = null;//学生类别
    private String appropriations = null;//经费办法
    private String teach_language = null;//授课语言
    private String school_roll_state = null;//学籍状态
    private String arrival_time_begin = "2015-01-01";//来华时间起始时间
    private String arrival_time_end = "2015-09-01";//来华时间终止时间
    private String leave_time_begin = "2015-01-01";//离华时间起始时间
    private String leave_time_end = "2015-09-01";//离华时间终止时间
    private String chn_frm_start_time_begin = "2015-01-01";//汉补开始时间起始时间
    private String chn_frm_start_time_end = "2015-09-01";//汉补开始时间终止时间
    private String chn_frm_end_time_begin = "2015-01-01";//汉补结束时间起始时间
    private String chn_frm_end_time_end = "2015-09-01";//汉补结束时间终止时间
    private String major_start_time_begin = "2015-01-01";//入专业院校时间起始时间
    private String major_start_time_end = "2015-09-01";//入专业院校时间终止时间
    private String plan_leave_time_begin = "2015-01-01";//预计离华时间起始时间
    private String plan_leave_time_end = "2015-09-01";//预计离华时间终止时间

    @Override
    public List getConditions() {
        List<FilterCell> conditions = new ArrayList<FilterCell>();
        conditions.add(new FilterCell("student", "csc_id", "String", getCsc_id()));
        conditions.add(new FilterCell("basicinfo", "passport_name", "String", getPassport_name()));
        conditions.add(new FilterCell("basicinfo", "continent_name", "String", getContinent_name()));
        conditions.add(new FilterCell("basicinfo", "country_name", "String", getCountry_name()));
        conditions.add(new FilterCell("basicinfo", "project_type", "String", getProject_type()));
        conditions.add(new FilterCell("basicinfo", "project_name", "String", getProject_name()));
        conditions.add(new FilterCell("basicinfo", "planned", "String", getPlanned()));
        conditions.add(new FilterCell("basicinfo", "dispatch", "String", getDispatch()));
        conditions.add(new FilterCell("basicinfo", "travel_type", "String", getTravel_type()));
        conditions.add(new FilterCell("basicinfo", "annual", "String", getAnnual()));

        conditions.add(new FilterCell("schoolroll", "register_state", "String", getRegister_state()));
        conditions.add(new FilterCell("schoolroll", "stu_type", "String", getStu_type()));
        conditions.add(new FilterCell("schoolroll", "appropriations", "String", getAppropriations()));
        conditions.add(new FilterCell("schoolroll", "teach_language", "String", getTeach_language()));
        conditions.add(new FilterCell("schoolroll", "school_roll_state", "String", getSchool_roll_state()));
        conditions.add(new FilterCell("schoolroll", "arrival_time", "Date", getArrival_time_begin() + "," + getArrival_time_end()));
        conditions.add(new FilterCell("schoolroll", "leave_time", "Date", getLeave_time_begin() + "," + getLeave_time_end()));
        conditions.add(new FilterCell("schoolroll", "chn_frm_start_time", "Date", getChn_frm_start_time_begin() + "," + getChn_frm_start_time_end()));
        conditions.add(new FilterCell("schoolroll", "chn_frm_end_time", "Date", getChn_frm_end_time_begin() + "," + getChn_frm_end_time_end()));
        conditions.add(new FilterCell("schoolroll", "major_start_time", "Date", getMajor_start_time_begin() + "," + getMajor_start_time_end()));
        conditions.add(new FilterCell("schoolroll", "plan_leave_time", "Date", getPlan_leave_time_begin() + "," + getPlan_leave_time_end()));

        return conditions;
    }

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public String getPassport_name() {
        return passport_name;
    }

    public void setPassport_name(String passport_name) {
        this.passport_name = passport_name;
    }

    public String getContinent_name() {
        return continent_name;
    }

    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getRegister_state() {
        return register_state;
    }

    public void setRegister_state(String register_state) {
        this.register_state = register_state;
    }

    public String getPlanned() {
        return planned;
    }

    public void setPlanned(String planned) {
        this.planned = planned;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getTravel_type() {
        return travel_type;
    }

    public void setTravel_type(String travel_type) {
        this.travel_type = travel_type;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    public String getStu_type() {
        return stu_type;
    }

    public void setStu_type(String stu_type) {
        this.stu_type = stu_type;
    }

    public String getAppropriations() {
        return appropriations;
    }

    public void setAppropriations(String appropriations) {
        this.appropriations = appropriations;
    }

    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
    }

    public String getSchool_roll_state() {
        return school_roll_state;
    }

    public void setSchool_roll_state(String school_roll_state) {
        this.school_roll_state = school_roll_state;
    }

    public String getArrival_time_begin() {
        return arrival_time_begin;
    }

    public void setArrival_time_begin(String arrival_time_begin) {
        this.arrival_time_begin = arrival_time_begin;
    }

    public String getArrival_time_end() {
        return arrival_time_end;
    }

    public void setArrival_time_end(String arrival_time_end) {
        this.arrival_time_end = arrival_time_end;
    }

    public String getLeave_time_begin() {
        return leave_time_begin;
    }

    public void setLeave_time_begin(String leave_time_begin) {
        this.leave_time_begin = leave_time_begin;
    }

    public String getLeave_time_end() {
        return leave_time_end;
    }

    public void setLeave_time_end(String leave_time_end) {
        this.leave_time_end = leave_time_end;
    }

    public String getChn_frm_start_time_begin() {
        return chn_frm_start_time_begin;
    }

    public void setChn_frm_start_time_begin(String chn_frm_start_time_begin) {
        this.chn_frm_start_time_begin = chn_frm_start_time_begin;
    }

    public String getChn_frm_start_time_end() {
        return chn_frm_start_time_end;
    }

    public void setChn_frm_start_time_end(String chn_frm_start_time_end) {
        this.chn_frm_start_time_end = chn_frm_start_time_end;
    }

    public String getChn_frm_end_time_begin() {
        return chn_frm_end_time_begin;
    }

    public void setChn_frm_end_time_begin(String chn_frm_end_time_begin) {
        this.chn_frm_end_time_begin = chn_frm_end_time_begin;
    }

    public String getChn_frm_end_time_end() {
        return chn_frm_end_time_end;
    }

    public void setChn_frm_end_time_end(String chn_frm_end_time_end) {
        this.chn_frm_end_time_end = chn_frm_end_time_end;
    }

    public String getMajor_start_time_begin() {
        return major_start_time_begin;
    }

    public void setMajor_start_time_begin(String major_start_time_begin) {
        this.major_start_time_begin = major_start_time_begin;
    }

    public String getMajor_start_time_end() {
        return major_start_time_end;
    }

    public void setMajor_start_time_end(String major_start_time_end) {
        this.major_start_time_end = major_start_time_end;
    }

    public String getPlan_leave_time_begin() {
        return plan_leave_time_begin;
    }

    public void setPlan_leave_time_begin(String plan_leave_time_begin) {
        this.plan_leave_time_begin = plan_leave_time_begin;
    }

    public String getPlan_leave_time_end() {
        return plan_leave_time_end;
    }

    public void setPlan_leave_time_end(String plan_leave_time_end) {
        this.plan_leave_time_end = plan_leave_time_end;
    }
}
