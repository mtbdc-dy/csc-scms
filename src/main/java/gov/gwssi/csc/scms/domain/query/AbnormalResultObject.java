package gov.gwssi.csc.scms.domain.query;

import java.util.Date;

/**
 * Created by lzs on 2015/4/29.
 * 异动申请管理查询学生信息列表结果类
 */
public class AbnormalResultObject extends ResultObject{
    /**
     * 学生ID
     */
    private String studentId;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * 性别
     */
    private String gender;

    /**
     * 护照姓名
     */
    private String passportName;

    /**
     * 申请人
     *
     */
    private String applyUserName;
    /**
     * 申请时间
     *
     */
    private Date applyTime;
    /**
     * 国籍
     */
    private String country;

    /**
     * 学籍状态
     */
    private String rollState;
    /**
     * 处理状态（ 0未提交 1提交未上报 2上报未审核 3审核未处理 4 处理）
     */
    private String handleState;
    //新增字段
    /**
     * 原因id
     */
    private String reasonId;
    /**
     * 原因
     */
    private String reason;
    /**
     * 异动上传pdf文件路径
     */
    private String applyUri;
    /**
     * 审批结果 0不同意 1同意
     */
    private String approResult;
    /**
     * 审批意见
     */
    private String approOpinion;
    /**
     * 发文pdf文件路径
     */
    private String publicUri;

    /**
     *  申请人ID
     */
    private String applyUserId;

    /**
     * 上报人ID
     */
    private String reportUserId;
    /**
     * 上报人
     */
    private String reportUserName;
    /**
     * 上报日期
     */
    private Date reportTime;
    /**
     * 审批人ID
     */
    private String approUserId;
    /**
     * 审批人
     */
    private String approUserName;
    /**
     * 审批日期
     */
    private Date approTime;
    /**
     * 处理人ID
     */
    private String handleUserId;
    /**
     * 处理人
     */
    private String handleUserName;
    /**
     * 处理日期
     */
    private Date handleTime;

    public AbnormalResultObject() {
    }

    public AbnormalResultObject(String studentId, String cscId, String gender, String passportName,
                                String applyUserName, Date applyTime, String country, String rollState, String handleState,
                                String reasonId, String reason, String applyUri, String approResult, String approOpinion,
                                String publicUri, String applyUserId, String reportUserId, String reportUserName,
                                Date reportTime, String approUserId, String approUserName, Date approTime,
                                String handleUserId, String handleUserName, Date handleTime) {
        this.studentId = studentId;
        this.cscId = cscId;
        this.gender = gender;
        this.passportName = passportName;
        this.applyUserName = applyUserName;
        this.applyTime = applyTime;
        this.country = country;
        this.rollState = rollState;
        this.handleState = handleState;
        this.reasonId = reasonId;
        this.reason = reason;
        this.applyUri = applyUri;
        this.approResult = approResult;
        this.approOpinion = approOpinion;
        this.publicUri = publicUri;
        this.applyUserId = applyUserId;
        this.reportUserId = reportUserId;
        this.reportUserName = reportUserName;
        this.reportTime = reportTime;
        this.approUserId = approUserId;
        this.approUserName = approUserName;
        this.approTime = approTime;
        this.handleUserId = handleUserId;
        this.handleUserName = handleUserName;
        this.handleTime = handleTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyUri() {
        return applyUri;
    }

    public void setApplyUri(String applyUri) {
        this.applyUri = applyUri;
    }

    public String getApproResult() {
        return approResult;
    }

    public void setApproResult(String approResult) {
        this.approResult = approResult;
    }

    public String getApproOpinion() {
        return approOpinion;
    }

    public void setApproOpinion(String approOpinion) {
        this.approOpinion = approOpinion;
    }

    public String getPublicUri() {
        return publicUri;
    }

    public void setPublicUri(String publicUri) {
        this.publicUri = publicUri;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(String reportUserId) {
        this.reportUserId = reportUserId;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getApproUserId() {
        return approUserId;
    }

    public void setApproUserId(String approUserId) {
        this.approUserId = approUserId;
    }

    public String getApproUserName() {
        return approUserName;
    }

    public void setApproUserName(String approUserName) {
        this.approUserName = approUserName;
    }

    public Date getApproTime() {
        return approTime;
    }

    public void setApproTime(Date approTime) {
        this.approTime = approTime;
    }

    public String getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(String handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getHandleUserName() {
        return handleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        this.handleUserName = handleUserName;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
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

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getRollState() {
        return rollState;
    }

    public void setRollState(String rollState) {
        this.rollState = rollState;
    }

    public String getHandleState() {
        return handleState;
    }

    public void setHandleState(String handleState) {
        this.handleState = handleState;
    }
    public static String getResultObject() {
        /**
         * String studentId, String cscId, String gender, String passportName,
         String applyUserName, Date applyTime, String country, String rollState, String handleState,
         String reasonId, String reason, String applyUri, String approResult, String approOpinion,
         String publicUri, String applyUserId, String reportUserId, String reportUserName,
         Date reportTime, String approUserId, String approUserName, Date approTime,
         String handleUserId, String handleUserName, Date handleTime
         */
        String resultSql = "select new gov.gwssi.csc.scms.domain.query.AbnormalResultObject(student.id, student.cscId, basicInfo.gender, " +
                "basicInfo.passportName, " +
                "abnormal.applyUserName,abnormal.applyTime,basicInfo.country,schoolRoll.state,abnormal.state,abnormal.reasonId," +
                " abnormal.reason,abnormal.applyUri,abnormal.approResult,abnormal.approOpinion,abnormal.publicUri,abnormal.applyUserId,abnormal.reportUserId," +
                "abnormal.reportUserName,abnormal.reportTime,abnormal.approUserId,abnormal.approUserName,abnormal.approTime,abnormal.handleUserId," +
                "abnormal.handleUserName,abnormal.handleTime) ";
        return resultSql;
    }
}
