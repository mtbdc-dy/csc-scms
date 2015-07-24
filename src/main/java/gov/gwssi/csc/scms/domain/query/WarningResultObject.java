package gov.gwssi.csc.scms.domain.query;

import java.util.Date;

/**
 * Created by tianjing on 2015/7/17.
 */
public class WarningResultObject extends ResultObject {

    /**
     * WarningId
     */
    private String warningId;
    /**
     * ѧ��Id
     */
    private String studentId;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * �Ա�
     */
    private String gender;
    /**
     * ��������
     */
    private Date birthday;
    /**
     * ��������
     */
    private String passportName;
    /**
     * ѧ����Ϣ
     * ֤������
     */
    private String certificateNO;
    /**
     * ����
     */
    private String country;
    /**
     * Ԥ�Ʊ�ҵʱ��
     */
    private Date planLeaveDate;
    /**
     * ��������
     */
    private Date arrivalDate;
    /**
     * ԺУ����
     */
    private String majorUniversity;
    /**
     * ��������
     */
    private Date addTime;

    /**
     * ������Ա
     */
    private String addUserName;

    public WarningResultObject(String warningId, String studentId, String cscId, String gender, Date birthday, String passportName, String certificateNO, String country, Date planLeaveDate, Date arrivalDate, String majorUniversity, Date addTime, String addUserName) {
        this.warningId = warningId;
        this.studentId = studentId;
        this.cscId = cscId;
        this.gender = gender;
        this.birthday = birthday;
        this.passportName = passportName;
        this.certificateNO = certificateNO;
        this.country = country;
        this.planLeaveDate = planLeaveDate;
        this.arrivalDate = arrivalDate;
        this.majorUniversity = majorUniversity;
        this.addTime = addTime;
        this.addUserName = addUserName;
    }

    public static String getResultObject() {
        String resultSql = "select new gov.gwssi.csc.scms.domain.query.WarningResultObject(warning.id, student.id, student.cscId, basicInfo.gender, " +
                "basicInfo.birthday, basicInfo.passportName,schoolRoll.certificateNO, " +
                "basicInfo.country, schoolRoll.planLeaveDate,schoolRoll.arrivalDate,schoolRoll.majorUniversity,warning.addTime,warning.addUserName) ";
        return resultSql;
    }

    public String getWarningId() {
        return warningId;
    }

    public void setWarningId(String warningId) {
        this.warningId = warningId;
    }

    public String getCscId() {
        return cscId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
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

    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public String getCertificateNO() {
        return certificateNO;
    }

    public void setCertificateNO(String certificateNO) {
        this.certificateNO = certificateNO;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getPlanLeaveDate() {
        return planLeaveDate;
    }

    public void setPlanLeaveDate(Date planLeaveDate) {
        this.planLeaveDate = planLeaveDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getMajorUniversity() {
        return majorUniversity;
    }

    public void setMajorUniversity(String majorUniversity) {
        this.majorUniversity = majorUniversity;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddUserName() {
        return addUserName;
    }

    public void setAddUserName(String addUserName) {
        this.addUserName = addUserName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
