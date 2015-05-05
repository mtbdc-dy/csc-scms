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
    private String sex;

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
     * 国籍
     */
    private String country;
    /**
     * 申请时间
     */
    private Date applyDate;
    /**
     * 学籍状态
     */
    private String rollState;
    /**
     * 处理状态（ 0未提交 1提交未上报 2上报未审核 3审核未处理 4 处理）
     */
    private String handleState;

    public AbnormalResultObject(String studentId, String cscId, String sex, String passportName, String applyUserName, String country, Date applyDate, String rollState, String handleState) {
        this.studentId = studentId;
        this.cscId = cscId;
        this.sex = sex;
        this.passportName = passportName;
        this.applyUserName = applyUserName;
        this.country = country;
        this.applyDate = applyDate;
        this.rollState = rollState;
        this.handleState = handleState;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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
         *  this.studentId = studentId;
         this.cscId = cscId;
         this.sex = sex;
         this.passportName = passportName;
         this.applyUserName = applyUserName;
         this.country = country;
         this.applyDate = applyDate;
         this.rollState = rollState;
         this.handleState = handleState;
         */
        String resultSql = "select new gov.gwssi.csc.scms.domain.query.AbnormalResultObject(student.id, student.cscId, basicInfo.gender, " +
                "basicInfo.passportName, " +
                "abnormal.applyUserName,basicInfo.country,abnormal.applyTime, schoolRoll.state,abnormal.state) ";
        return resultSql;
    }
}
