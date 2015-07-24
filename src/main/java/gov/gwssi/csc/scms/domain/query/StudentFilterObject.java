package gov.gwssi.csc.scms.domain.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生列表查询实体类
 * Created by Murray on 2015/4/2.
 */
public class StudentFilterObject extends FilterObject {

    /**
     * 主要查询条件
     */
    private String cscId = null;//SCS登记号
    private String passportName = null;//护照名称
    private String continent = null;//洲别
    private String country = null;//国籍
    private String projectType = null;//项目类别
    private String projectName = null;//项目名称
    private String registerState = null;//报到状态 0未处理 1报到 2放弃来华
    private String abnormalState = null;//异动处理状态
    private String abnormalDateBegin = null;//异动申请起始日期
    private String abnormalDateEnd = null;//异动申请终止日期
    private String ticketState = null;//异动处理状态
    private String insuranceState = null;//保险订购状态 add by gc
    /**
     * 隐藏查询条件
     */
    private String planned = null;//名额性质（计划内 计划外）
    private String dispatch = null;//派遣途径
    private String travelType = null;//国际旅费
    private String annual = null; //年度
    private String studentType = null;//学生类别
    private String appropriation = null;//经费办法
    private String teachLanguage = null;//授课语言
    private String schoolRollState = null;//学籍状态
    private String arrivalDateBegin = null;//来华时间起始时间
    private String arrivalDateEnd = null;//来华时间终止时间
    private String leaveDateBegin = null;//离华时间起始时间
    private String leaveDateEnd = null;//离华时间终止时间
    private String cramDateBeginBegin = null;//汉补开始时间起始时间
    private String cramDateBeginEnd = null;//汉补开始时间终止时间
    private String cramDateEndBegin = null;//汉补结束时间起始时间
    private String cramDateEndEnd = null;//汉补结束时间终止时间
    private String majorStartDateBegin = null;//入专业院校时间起始时间
    private String majorStartDateEnd = null;//入专业院校时间终止时间
    private String planLeaveDateBegin = null;//预计离华时间起始时间
    private String planLeaveDateEnd = null;//预计离华时间终止时间
    //add by lzs20150511添加mode字段用来区分新增学生列表时候，返回不同列表
    private String mode = null;//区分学生列表
    public List<FilterCell> getConditions() {
        List<FilterCell> conditions = new ArrayList<FilterCell>();

        conditions = addCondition(conditions, "basicInfo", "passportName", "String", getPassportName());
        conditions = addCondition(conditions, "basicInfo", "continent", "String", getContinent());
        conditions = addCondition(conditions, "basicInfo", "country", "String", getCountry());
        conditions = addCondition(conditions, "basicInfo", "projectType", "String", getProjectType());
        conditions = addCondition(conditions, "basicInfo", "projectName", "String", getProjectName());
        conditions = addCondition(conditions, "basicInfo", "planned", "String", getPlanned());
        conditions = addCondition(conditions, "basicInfo", "dispatch", "String", getDispatch());
        conditions = addCondition(conditions, "basicInfo", "travelType", "String", getTravelType());
        conditions = addCondition(conditions, "basicInfo", "annual", "String", getAnnual());
        conditions = addCondition(conditions, "abnormal", "state", "String", getAbnormalState());
        conditions = addCondition(conditions, "abnormal", "applyTime", "date", getAbnormalDateBegin(), getAbnormalDateEnd());

        conditions = addCondition(conditions, "schoolRoll", "registerState", "String", getRegisterState());
        conditions = addCondition(conditions, "schoolRoll", "studentType", "String", getStudentType());
        conditions = addCondition(conditions, "schoolRoll", "appropriation", "String", getAppropriation());
        conditions = addCondition(conditions, "schoolRoll", "teachLanguage", "String", getTeachLanguage());
        conditions = addCondition(conditions, "schoolRoll", "state", "String", getSchoolRollState());
        conditions = addCondition(conditions, "schoolRoll", "arrivalDate", "date", getArrivalDateBegin(), getArrivalDateEnd());
        conditions = addCondition(conditions, "schoolRoll", "leaveDate", "date", getLeaveDateBegin(), getLeaveDateEnd());
        conditions = addCondition(conditions, "schoolRoll", "cramDateBegin", "date", getCramDateBeginBegin(), getCramDateBeginEnd());
        conditions = addCondition(conditions, "schoolRoll", "cramDateEnd", "date", getCramDateEndBegin(), getCramDateEndEnd());
        conditions = addCondition(conditions, "schoolRoll", "majorStartDate", "date", getMajorStartDateBegin(), getMajorStartDateEnd());
        conditions = addCondition(conditions, "schoolRoll", "planLeaveDate", "date", getPlanLeaveDateBegin(), getPlanLeaveDateEnd());
        conditions = addCondition(conditions, "ticket", "state", "String", getTicketState());
        conditions = addCondition(conditions, "Insurance", "preSta", "String", getInsuranceState());

        conditions = addCondition(conditions, "student", "cscId", "String", getCscId());
        //将mode字段添加到条件中
        conditions = addCondition(conditions, "", "", "mode", getMode());

        return conditions;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }

    public String getInsuranceState() {
        return insuranceState;
    }

    public void setInsuranceState(String insuranceState) {
        this.insuranceState = insuranceState;
    }

    public String getCscId() {
        return cscId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRegisterState() {
        return registerState;
    }

    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }

    public String getAbnormalState() {
        return abnormalState;
    }

    public void setAbnormalState(String abnormalState) {
        this.abnormalState = abnormalState;
    }

    public String getAbnormalDateBegin() {
        return abnormalDateBegin;
    }

    public void setAbnormalDateBegin(String abnormalDateBegin) {
        this.abnormalDateBegin = abnormalDateBegin;
    }

    public String getAbnormalDateEnd() {
        return abnormalDateEnd;
    }

    public void setAbnormalDateEnd(String abnormalDateEnd) {
        this.abnormalDateEnd = abnormalDateEnd;
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

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getAppropriation() {
        return appropriation;
    }

    public void setAppropriation(String appropriation) {
        this.appropriation = appropriation;
    }

    public String getTeachLanguage() {
        return teachLanguage;
    }

    public void setTeachLanguage(String teachLanguage) {
        this.teachLanguage = teachLanguage;
    }

    public String getSchoolRollState() {
        return schoolRollState;
    }

    public void setSchoolRollState(String schoolRollState) {
        this.schoolRollState = schoolRollState;
    }

    public String getArrivalDateBegin() {
        return arrivalDateBegin;
    }

    public void setArrivalDateBegin(String arrivalDateBegin) {
        this.arrivalDateBegin = arrivalDateBegin;
    }

    public String getArrivalDateEnd() {
        return arrivalDateEnd;
    }

    public void setArrivalDateEnd(String arrivalDateEnd) {
        this.arrivalDateEnd = arrivalDateEnd;
    }

    public String getLeaveDateBegin() {
        return leaveDateBegin;
    }

    public void setLeaveDateBegin(String leaveDateBegin) {
        this.leaveDateBegin = leaveDateBegin;
    }

    public String getLeaveDateEnd() {
        return leaveDateEnd;
    }

    public void setLeaveDateEnd(String leaveDateEnd) {
        this.leaveDateEnd = leaveDateEnd;
    }

    public String getCramDateBeginBegin() {
        return cramDateBeginBegin;
    }

    public void setCramDateBeginBegin(String cramDateBeginBegin) {
        this.cramDateBeginBegin = cramDateBeginBegin;
    }

    public String getCramDateBeginEnd() {
        return cramDateBeginEnd;
    }

    public void setCramDateBeginEnd(String cramDateBeginEnd) {
        this.cramDateBeginEnd = cramDateBeginEnd;
    }

    public String getCramDateEndBegin() {
        return cramDateEndBegin;
    }

    public void setCramDateEndBegin(String cramDateEndBegin) {
        this.cramDateEndBegin = cramDateEndBegin;
    }

    public String getCramDateEndEnd() {
        return cramDateEndEnd;
    }

    public void setCramDateEndEnd(String cramDateEndEnd) {
        this.cramDateEndEnd = cramDateEndEnd;
    }

    public String getMajorStartDateBegin() {
        return majorStartDateBegin;
    }

    public void setMajorStartDateBegin(String majorStartDateBegin) {
        this.majorStartDateBegin = majorStartDateBegin;
    }

    public String getMajorStartDateEnd() {
        return majorStartDateEnd;
    }

    public void setMajorStartDateEnd(String majorStartDateEnd) {
        this.majorStartDateEnd = majorStartDateEnd;
    }

    public String getPlanLeaveDateBegin() {
        return planLeaveDateBegin;
    }

    public void setPlanLeaveDateBegin(String planLeaveDateBegin) {
        this.planLeaveDateBegin = planLeaveDateBegin;
    }

    public String getPlanLeaveDateEnd() {
        return planLeaveDateEnd;
    }

    public void setPlanLeaveDateEnd(String planLeaveDateEnd) {
        this.planLeaveDateEnd = planLeaveDateEnd;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
