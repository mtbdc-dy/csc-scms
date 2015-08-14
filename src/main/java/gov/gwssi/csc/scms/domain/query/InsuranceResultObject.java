package gov.gwssi.csc.scms.domain.query;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gc on 2015/7/17.
 * 机票管理信息列表结果类
 */
public class InsuranceResultObject extends ResultObject{
    /**
     * ID
     */
    private String id;
    /**
     * ID
     */
    private String studentId;
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
     * 国籍
     */
    private String country;

    /**
     * 来华日期
     */
    private Date arrivalDate;

    /**
     * 经费办法
     */
    private String appropriation;
    /**
     * 院校名称
     */
    private String currentUniversity;
    /**
     * 预计毕业日期
     */
    private Date planLeaveDate;

    /**
     * 保险单号码
     */
    private String insurNo;

    /**
     *预定状态：
     * 参考代码表
     */
    private String preSta;



    public InsuranceResultObject(String id, String studentId, String cscId, String passportName, String gender,
                                 Date birthday, Date arrivalDate, String country, String appropriation,
                                 String currentUniversity, Date planLeaveDate,String preSta, String insurNo
                                 ) {
        this.id = id;
        this.studentId = studentId;
        this.cscId = cscId;
        this.passportName = passportName;
        this.gender = gender;
        this.birthday = birthday;
        this.arrivalDate = arrivalDate;
        this.country = country;
        this.appropriation = appropriation;
        this.currentUniversity = currentUniversity;
        this.planLeaveDate = planLeaveDate;
        this.preSta = preSta;
        this.insurNo = insurNo;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getAppropriation() {
        return appropriation;
    }

    public void setAppropriation(String appropriation) {
        this.appropriation = appropriation;
    }


    public String getCurrentUniversity() {
        return currentUniversity;
    }

    public void setCurrentUniversity(String currentUniversity) {
        this.currentUniversity = currentUniversity;
    }

    public Date getPlanLeaveDate() {
        return planLeaveDate;
    }

    public void setPlanLeaveDate(Date planLeaveDate) {
        this.planLeaveDate = planLeaveDate;
    }

    public String getInsurNo() {
        return insurNo;
    }

    public void setInsurNo(String insurNo) {
        this.insurNo = insurNo;
    }

    public String getPreSta() {
        return preSta;
    }

    public void setPreSta(String preSta) {
        this.preSta = preSta;
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


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }








    public static String getResultObject() {

        String resultSql = "select new gov.gwssi.csc.scms.domain.query.InsuranceResultObject(Insurance.id,student.id, student.cscId, basicInfo.passportName,basicInfo.gender, " +
                "basicInfo.birthday,schoolRoll.arrivalDate,basicInfo.country,schoolRoll.appropriation,schoolRoll.currentUniversity,schoolRoll.planLeaveDate," +
                "  Insurance.preSta,Insurance.insurNo) ";
        return resultSql;
    }
}
