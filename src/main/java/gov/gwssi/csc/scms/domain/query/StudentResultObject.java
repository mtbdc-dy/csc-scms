package gov.gwssi.csc.scms.domain.query;

import java.util.Date;

/**
 * 学籍信息管理查询学生信息列表结果类
 * Created by Murray on 2015/4/20.
 */
public class StudentResultObject extends ResultObject {
    /**
     * 学生ID
     */
    private String id;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * 性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date birthday;
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
    //add by lzs20150511异动申请页面需要的一个字段
    /**
     * 州别
     */
    private String continent;

    public StudentResultObject(String id, String cscId, String gender, Date birthday, String passportName, String certificateNO, String studentType, String country, Date planLeaveDate, String registerState, String continent) {
        this.id = id;
        this.cscId = cscId;
        this.gender = gender;
        this.birthday = birthday;
        this.passportName = passportName;
        this.certificateNO = certificateNO;
        this.studentType = studentType;
        this.country = country;
        this.planLeaveDate = planLeaveDate;
        this.registerState = registerState;
        this.continent = continent;
    }


    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCscId() {
        return cscId;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
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

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public static String getResultObject() {
        String resultSql = "select new gov.gwssi.csc.scms.domain.query.StudentResultObject(student.id, student.cscId, basicInfo.gender, " +
                "basicInfo.birthday, basicInfo.passportName,schoolRoll.certificateNO, schoolRoll.studentType, " +
                "basicInfo.country, schoolRoll.planLeaveDate,schoolRoll.registerState,basicInfo.continent) ";
        return resultSql;
    }
}
