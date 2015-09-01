package gov.gwssi.csc.scms.domain.filter;

import java.util.Date;

/**
 * Created by wang Zishi on 15/8/8.
 * 过滤器实体类
 */
public class Filter {
    /**
     * 主要查询条件
     */
    private String cscId = null;             // CSC登记号
    private String passportName = null;      // 护照名称
    private String continent = null;         // 洲别
    private String country = null;           // 国籍
    private String projectAttr = null;       // 项目属性
    private String projectType = null;       // 项目类别
    private String projectName = null;       // 项目名称
    private String registerState = null;     // 报到状态 0未处理 1报到 2放弃来华
    private String abnormalState = null;     // 异动处理状态
    private Date abnormalDateBegin = null;   // 异动申请起始日期
    private Date abnormalDateEnd = null;     // 异动申请终止日期
    private String ticketState = null;       // 异动处理状态
    private String preSta = null;            // 保险订购状态 add by gc
    private String schReview = null;         // 奖学金评审结果 add by gc
    private String schResult = null;         // 奖学金处理结果 add by gc
    private int year = 0;                    //奖学金评审年度
/**
 * LZS 自定义的查询条件 非公共条件
 */
    private String pro = null;//省市
    private String univ = null;//学校
    /**
     * 隐藏查询条件
     */
    private String planned = null;           // 名额性质（计划内 计划外）
    private String dispatch = null;          // 派遣途径
    private String travelType = null;        // 国际旅费
    private Integer annual = null;              // 年度
    private String studentType = null;       // 学生类别
    private String appropriation = null;     // 经费办法
    private String teachLanguage = null;     // 授课语言
    private String schoolRollState = null;   // 学籍状态
    private Date arrivalDateBegin = null;    // 来华时间起始时间
    private Date arrivalDateEnd = null;      // 来华时间终止时间
    private Date leaveDateBegin = null;      // 离华时间起始时间
    private Date leaveDateEnd = null;        // 离华时间终止时间
    private Date cramDateBeginBegin = null;  // 汉补开始时间起始时间
    private Date cramDateBeginEnd = null;    // 汉补开始时间终止时间
    private Date cramDateEndBegin = null;    // 汉补结束时间起始时间
    private Date cramDateEndEnd = null;      // 汉补结束时间终止时间
    private Date majorStartDateBegin = null; // 入专业院校时间起始时间
    private Date majorStartDateEnd = null;   // 入专业院校时间终止时间
    private Date planLeaveDateBegin = null;  // 预计离华时间起始时间
    private Date planLeaveDateEnd = null;    // 预计离华时间终止时间

    // add by lzs20150511 添加 mode 字段用来区分新增学生列表时候，返回不同列表
    private String mode = null; // 区分学生列表

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

    public String getProjectAttr() {
        return projectAttr;
    }

    public void setProjectAttr(String projectAttr) {
        this.projectAttr = projectAttr;
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

    public Date getAbnormalDateBegin() {
        return abnormalDateBegin;
    }

    public void setAbnormalDateBegin(Date abnormalDateBegin) {
        this.abnormalDateBegin = abnormalDateBegin;
    }

    public Date getAbnormalDateEnd() {
        return abnormalDateEnd;
    }

    public void setAbnormalDateEnd(Date abnormalDateEnd) {
        this.abnormalDateEnd = abnormalDateEnd;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }

    public String getSchReview() {
        return schReview;
    }

    public void setSchReview(String schReview) {
        this.schReview = schReview;
    }

    public String getSchResult() {
        return schResult;
    }

    public void setSchResult(String schResult) {
        this.schResult = schResult;
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

    public Integer getAnnual() {
        return annual;
    }

    public void setAnnual(Integer annual) {
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

    public Date getArrivalDateBegin() {
        return arrivalDateBegin;
    }

    public void setArrivalDateBegin(Date arrivalDateBegin) {
        this.arrivalDateBegin = arrivalDateBegin;
    }

    public Date getArrivalDateEnd() {
        return arrivalDateEnd;
    }

    public void setArrivalDateEnd(Date arrivalDateEnd) {
        this.arrivalDateEnd = arrivalDateEnd;
    }

    public Date getLeaveDateBegin() {
        return leaveDateBegin;
    }

    public void setLeaveDateBegin(Date leaveDateBegin) {
        this.leaveDateBegin = leaveDateBegin;
    }

    public Date getLeaveDateEnd() {
        return leaveDateEnd;
    }

    public void setLeaveDateEnd(Date leaveDateEnd) {
        this.leaveDateEnd = leaveDateEnd;
    }

    public Date getCramDateBeginBegin() {
        return cramDateBeginBegin;
    }

    public void setCramDateBeginBegin(Date cramDateBeginBegin) {
        this.cramDateBeginBegin = cramDateBeginBegin;
    }

    public Date getCramDateBeginEnd() {
        return cramDateBeginEnd;
    }

    public void setCramDateBeginEnd(Date cramDateBeginEnd) {
        this.cramDateBeginEnd = cramDateBeginEnd;
    }

    public Date getCramDateEndBegin() {
        return cramDateEndBegin;
    }

    public void setCramDateEndBegin(Date cramDateEndBegin) {
        this.cramDateEndBegin = cramDateEndBegin;
    }

    public Date getCramDateEndEnd() {
        return cramDateEndEnd;
    }

    public void setCramDateEndEnd(Date cramDateEndEnd) {
        this.cramDateEndEnd = cramDateEndEnd;
    }

    public Date getMajorStartDateBegin() {
        return majorStartDateBegin;
    }

    public void setMajorStartDateBegin(Date majorStartDateBegin) {
        this.majorStartDateBegin = majorStartDateBegin;
    }

    public Date getMajorStartDateEnd() {
        return majorStartDateEnd;
    }

    public void setMajorStartDateEnd(Date majorStartDateEnd) {
        this.majorStartDateEnd = majorStartDateEnd;
    }

    public Date getPlanLeaveDateBegin() {
        return planLeaveDateBegin;
    }

    public void setPlanLeaveDateBegin(Date planLeaveDateBegin) {
        this.planLeaveDateBegin = planLeaveDateBegin;
    }

    public Date getPlanLeaveDateEnd() {
        return planLeaveDateEnd;
    }

    public void setPlanLeaveDateEnd(Date planLeaveDateEnd) {
        this.planLeaveDateEnd = planLeaveDateEnd;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }

    public String getPreSta() {
        return preSta;
    }

    public void setPreSta(String preSta) {
        this.preSta = preSta;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
