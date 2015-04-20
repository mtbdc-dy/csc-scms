package gov.gwssi.csc.scms.domain.queryfilter;

import org.springframework.util.StringUtils;

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
    private String cscId = null;//SCS登记号
    private String passportName = null;//护照名称
    private String continent = null;//洲别
    private String country = null;//国籍
    private String projectType = null;//项目类别
    private String projectName = null;//项目名称
    private String registerState = null;//报到状态 0未处理 1报到 2放弃来华

    /**
     * 隐藏查询条件
     */
    private String planned = null;//名额性质（计划内 计划外）
    private String dispatch = null;//派遣途径
    private String travelType = null;//国际旅费
    private String annual = null; //年度
    private String studentType = null;//学生类别
    private String appropriations = null;//经费办法
    private String teachLanguage = null;//授课语言
    private String schoolRollState = null;//学籍状态
    private String arrivalDateBegin = "2015-01-01";//来华时间起始时间
    private String arrivalDateEnd = "2015-09-01";//来华时间终止时间
    private String leaveDateBegin = "2015-01-01";//离华时间起始时间
    private String leaveDateEnd = "2015-09-01";//离华时间终止时间
    private String cramDateBeginBegin = "2015-01-01";//汉补开始时间起始时间
    private String cramDateBeginEnd = "2015-09-01";//汉补开始时间终止时间
    private String cramDateEndBegin = "2015-01-01";//汉补结束时间起始时间
    private String cramDateEndEnd = "2015-09-01";//汉补结束时间终止时间
    private String majorStartDateBegin = "2015-01-01";//入专业院校时间起始时间
    private String majorStartDateEnd = "2015-09-01";//入专业院校时间终止时间
    private String planLeaveDateBegin = "2015-01-01";//预计离华时间起始时间
    private String planLeaveDateEnd = "2015-09-01";//预计离华时间终止时间

    public List<FilterCell> getConditions() {
        List<FilterCell> conditions = new ArrayList<FilterCell>();
        conditions = addCondition(conditions, "student", "cscid", "String", getCscId());

        conditions = addCondition(conditions, "basicinfo", "passportName", "String", getPassportName());
        conditions = addCondition(conditions, "basicinfo", "continent", "String", getContinent());
        conditions = addCondition(conditions, "basicinfo", "country", "String", getCountry());
        conditions = addCondition(conditions, "basicinfo", "projectType", "String", getProjectType());
        conditions = addCondition(conditions, "basicinfo", "projectName", "String", getProjectName());
        conditions = addCondition(conditions, "basicinfo", "planted", "String", getPlanned());
        conditions = addCondition(conditions, "basicinfo", "dispatch", "String", getDispatch());
        conditions = addCondition(conditions, "basicinfo", "travleType", "String", getTravelType());
        conditions = addCondition(conditions, "basicinfo", "annual", "String", getAnnual());

        conditions = addCondition(conditions, "schoolroll", "registerState", "String", getRegisterState());
        conditions = addCondition(conditions, "schoolroll", "studentType", "String", getStudentType());
        conditions = addCondition(conditions, "schoolroll", "appropriations", "String", getAppropriations());
        conditions = addCondition(conditions, "schoolroll", "teachlanguage", "String", getTeachLanguage());
        conditions = addCondition(conditions, "schoolroll", "schoolrollstate", "String", getSchoolRollState());
        conditions = addCondition(conditions, "schoolroll", "arrivalDate", "date", getArrivalDateBegin(), getArrivalDateEnd());
        conditions = addCondition(conditions, "schoolroll", "leaveDate", "date", getLeaveDateBegin(), getLeaveDateEnd());
        conditions = addCondition(conditions, "schoolroll", "cramDateBegin", "date", getCramDateBeginBegin(), getCramDateBeginEnd());
        conditions = addCondition(conditions, "schoolroll", "cramDateEnd", "date", getCramDateEndBegin(), getCramDateEndEnd());
        conditions = addCondition(conditions, "schoolroll", "majorDate", "date", getMajorStartDateBegin(), getMajorStartDateEnd());
        conditions = addCondition(conditions, "schoolroll", "plan_leave_time", "date", getPlanLeaveDateBegin(), getPlanLeaveDateEnd());

        return conditions;
    }

    private List<FilterCell> addCondition(List<FilterCell> conditions, String tableName, String filedName, String type, String value) {
        if (isNull(value))
            return conditions;
        conditions.add(new FilterCell(tableName, filedName, type, value));
        return conditions;
    }

    private List<FilterCell> addCondition(List<FilterCell> conditions, String tableName, String filedName, String type, String value1, String value2) {
        if (isNull(value1) || isNull(value2))
            return conditions;
        conditions.add(new FilterCell(tableName, filedName, type, value1 + "," + value2));
        return conditions;
    }

    private boolean isNull(String value) {
        return ("null".equalsIgnoreCase(value) || StringUtils.isEmpty(value));
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

    public String getAppropriations() {
        return appropriations;
    }

    public void setAppropriations(String appropriations) {
        this.appropriations = appropriations;
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
}
