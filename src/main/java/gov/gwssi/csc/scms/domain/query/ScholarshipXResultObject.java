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
     * 处理原因
     */
    private String reason;

    /**
     * 不合格时间起
     */
    private Date startTime;

    /**
     * 不合格时间止
     */
    private Date endTime;

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
     *合格人数
     */

    private Long qualNum;

    /**
     *不合格人数
     */

    private Long unQualNum;

    public ScholarshipXResultObject(String id, String studentId, String scholarshipId, String cscId, String passportName, String gender,
                                    Date birthday, String schReview, String schResult, Long year, Long qualNum, Long unQualNum,
                                    String reason, Date startTime, Date endTime, String cscrresult_lastyear, String schoolSta, String cscSta
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
        this.year=year;
        this.qualNum = qualNum;
        this.unQualNum = unQualNum;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cscrresult_lastyear = cscrresult_lastyear;
        this.schoolSta = schoolSta;
        this.cscSta = cscSta;
    }

    public static String getResultObject() {

        String resultSql = "select new gov.gwssi.csc.scms.domain.query.ScholarshipXResultObject(" +
                "ScholarshipX.id,ScholarshipX.studentId,ScholarshipX.scholarshipId, student.cscId, basicInfo.passportName,basicInfo.gender," +
                "basicInfo.birthday,ScholarshipX.schReview,ScholarshipX.schResult," +
                "ScholarshipX.year,ScholarshipX.qualNum,ScholarshipX.unQualNum," +
                "ScholarshipX.reason,ScholarshipX.startTime," +
                "ScholarshipX.endTime,ScholarshipX.cscrresult_lastyear,ScholarshipX.schoolSta,ScholarshipX.cscSta)";
        return resultSql;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getQualNum() {
        return qualNum;
    }

    public void setQualNum(Long qualNum) {
        this.qualNum = qualNum;
    }

    public Long getUnQualNum() {
        return unQualNum;
    }

    public void setUnQualNum(Long unQualNum) {
        this.unQualNum = unQualNum;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
