package gov.gwssi.csc.scms.domain.query;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 学籍信息管理查询学生信息列表结果类
 * Created by Murray on 2015/4/20.
 */
public class StudentResultObject extends ResultObject {
    /**
     * 学生ID
     */
    private Long studentId;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 护照姓名
     */
    private String passportName;
    /**
     * 学籍信息
     * 证件号码
     */
    private String certificateNO;
    /**
     * 学籍信息
     * 学生类别
     */
    private String studentType;
    /**
     * 国籍
     */
    private String country;
    /**
     * 预计毕业时间
     */
    private Date planLeaveDate;
    /**
     * 学籍信息
     * 报到状态（0未处理 1报到 2放弃来华）
     */
    private String registerState;

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public void setCertificateNO(String certificateNO) {
        this.certificateNO = certificateNO;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPlanLeaveDate(Date planLeaveDate) {
        this.planLeaveDate = planLeaveDate;
    }

    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getCscId() {
        return cscId;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPassportName() {
        return passportName;
    }

    public String getCertificateNO() {
        return certificateNO;
    }

    public String getStudentType() {
        return studentType;
    }

    public String getCountry() {
        return country;
    }

    public Date getPlanLeaveDate() {
        return planLeaveDate;
    }

    public String getRegisterState() {
        return registerState;
    }

    public static String getResultObject() {

        String resultSql = "select student.id as studentId, student.cscId as cscId, basicInfo.sex as sex, " +
                "basicInfo.birthday as birthday, basicInfo.passportName as passportName, " +
                "schoolRoll.certificateNO as certificateNO, schoolRoll.studentType as studentType, " +
                "basicInfo.country as country, schoolRoll.planLeaveDate as planLeaveDate, " +
                "schoolRoll.registerState as registerState";
        return resultSql;
    }
}
