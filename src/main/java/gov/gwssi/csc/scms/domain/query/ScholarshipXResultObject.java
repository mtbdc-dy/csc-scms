package gov.gwssi.csc.scms.domain.query;


import java.util.Date;

/**
 * Created by gc on 2015/7/24.
 * 奖学金评审管理信息列表结果类
 */
public class ScholarshipXResultObject extends ResultObject{
    /**
     * ID   奖学金子表id
     */
    private String id;

    /**
     * scholarshipId   主表ID
     */
    private String scholarshipId;
    /**
     * ID
     */
    private String studentId;
    /**
     * 年度
     */
    private Long year;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * 护照姓名
     */
    private String passportName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date birthday;


    /**
     * 评审结果
     */
    private String schReview;

    /**
     * 处理结果
     */
    private String schResult;
    /**
     * 评审结果
     */
    private String cscReview;

    /**
     * 处理结果
     */
    private String cscResult;

    /**
     * 处理原因
     */
    private String schReason;

    /**
     * 不合格时间起
     */
    private Date schStartTime;

    /**
     * 不合格时间止
     */
    private Date schEndTime;
    /**
     * 处理原因
     */
    private String cscReason;

    /**
     * 不合格时间起
     */
    private Date cscStartTime;

    private Date updated;

    private String updateby;

    /**
     * 不合格时间止
     */
    private Date cscEndTime;
    /**
     * 上年评审结果
     */
    private String cscrresult_lastyear;

    /**
     *学校提交状态：
     * 参考代码表
     */
    private String schoolSta;
    /**
     *基金委提交状态：
     * 参考代码表
     */
    private String cscSta;
    /**
     *合格人数——学校用户
     */
    private Long schoolQual;

    /**
     *不合格人数——学校用户
     */
    private Long schoolUnQual;
    /**
     *合格人数--基金委用户
     */
    private Long cscQual;

    /**
     *不合格人数--基金委用户
     */
    private Long cscUnQual;

    public ScholarshipXResultObject(String id, String studentId, String scholarshipId, String cscId, String passportName, String gender,
                                    Date birthday, String schReview, String schResult,String cscReview, String cscResult, Long year,
                                    Long schoolQual,Long schoolUnQual , Long cscQual, Long cscUnQual,
                                    Date updated,String updateby,
                                    String schReason, Date schStartTime, Date schEndTime, String cscReason, Date cscStartTime, Date cscEndTime, String cscrresult_lastyear, String schoolSta, String cscSta
    ) {
        this.id = id;
        this.studentId = studentId;
        this.scholarshipId=scholarshipId;
        this.cscId = cscId;
        this.passportName = passportName;
        this.gender = gender;
        this.birthday = birthday;
        this.schReview = schReview;
        this.schResult = schResult;
        this.cscReview = cscReview;
        this.cscResult = cscResult;
        this.year=year;
        this.schoolQual = schoolQual;
        this.schoolUnQual=schoolUnQual;
        this.cscQual = cscQual;
        this.cscUnQual=cscUnQual;
        this.updated=updated;
        this.updateby=updateby;
        this.schReason = schReason;
        this.schStartTime = schStartTime;
        this.schEndTime = schEndTime;
        this.cscReason = cscReason;
        this.cscStartTime = cscStartTime;
        this.cscEndTime = cscEndTime;
        this.cscrresult_lastyear = cscrresult_lastyear;
        this.schoolSta = schoolSta;
        this.cscSta = cscSta;
    }

    public static String getResultObject() {

        String resultSql = "select new gov.gwssi.csc.scms.domain.query.ScholarshipXResultObject(" +
                "ScholarshipX.id,student.id,ScholarshipX.scholarshipId, student.cscId, basicInfo.passportName,basicInfo.gender," +
                "basicInfo.birthday,ScholarshipX.schReview,ScholarshipX.schResult,ScholarshipX.cscReview,ScholarshipX.cscResult," +
                "ScholarshipX.year,ScholarshipX.schoolQual,ScholarshipX.schoolUnQual,ScholarshipX.cscQual,ScholarshipX.cscUnQual," +
                "ScholarshipX.updated,ScholarshipX.updateby," +
                "ScholarshipX.schReason,ScholarshipX.schStartTime,ScholarshipX.schEndTime," +
                "ScholarshipX.cscReason,ScholarshipX.cscStartTime,ScholarshipX.cscEndTime," +
                "ScholarshipX.cscrresult_lastyear,ScholarshipX.schoolSta,ScholarshipX.cscSta)";
        return resultSql;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public String getCscReview() {
        return cscReview;
    }

    public void setCscReview(String cscReview) {
        this.cscReview = cscReview;
    }

    public String getCscResult() {
        return cscResult;
    }

    public void setCscResult(String cscResult) {
        this.cscResult = cscResult;
    }

    public String getSchReason() {
        return schReason;
    }

    public void setSchReason(String schReason) {
        this.schReason = schReason;
    }

    public Date getSchStartTime() {
        return schStartTime;
    }

    public void setSchStartTime(Date schStartTime) {
        this.schStartTime = schStartTime;
    }

    public Date getSchEndTime() {
        return schEndTime;
    }

    public void setSchEndTime(Date schEndTime) {
        this.schEndTime = schEndTime;
    }

    public String getCscReason() {
        return cscReason;
    }

    public void setCscReason(String cscReason) {
        this.cscReason = cscReason;
    }

    public Date getCscStartTime() {
        return cscStartTime;
    }

    public void setCscStartTime(Date cscStartTime) {
        this.cscStartTime = cscStartTime;
    }

    public Date getCscEndTime() {
        return cscEndTime;
    }

    public void setCscEndTime(Date cscEndTime) {
        this.cscEndTime = cscEndTime;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getSchoolQual() {
        return schoolQual;
    }

    public void setSchoolQual(Long schoolQual) {
        this.schoolQual = schoolQual;
    }

    public Long getSchoolUnQual() {
        return schoolUnQual;
    }

    public void setSchoolUnQual(Long schoolUnQual) {
        this.schoolUnQual = schoolUnQual;
    }

    public Long getCscQual() {
        return cscQual;
    }

    public void setCscQual(Long cscQual) {
        this.cscQual = cscQual;
    }

    public Long getCscUnQual() {
        return cscUnQual;
    }

    public void setCscUnQual(Long cscUnQual) {
        this.cscUnQual = cscUnQual;
    }

    public String getCscSta() {
        return cscSta;
    }

    public void setCscSta(String scsSta) {
        this.cscSta = cscSta;
    }

    public String getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(String scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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



    public String getCscrresult_lastyear() {
        return cscrresult_lastyear;
    }

    public void setCscrresult_lastyear(String cscrresult_lastyear) {
        this.cscrresult_lastyear = cscrresult_lastyear;
    }

    public String getSchoolSta() {
        return schoolSta;
    }

    public void setSchoolSta(String schoolSta) {
        this.schoolSta = schoolSta;
    }
}
