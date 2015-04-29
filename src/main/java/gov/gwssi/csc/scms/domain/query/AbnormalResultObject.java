package gov.gwssi.csc.scms.domain.query;

import java.util.Date;

/**
 * Created by lzs on 2015/4/29.
 * �춯��������ѯѧ����Ϣ�б�����
 */
public class AbnormalResultObject extends ResultObject{
    /**
     * ѧ��ID
     */
    private Long studentId;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * �Ա�
     */
    private String sex;

    /**
     * ��������
     */
    private String passportName;

    /**
     * ������
     *
     */
    private String applyUserName;
    /**
     * ����
     */
    private String country;
    /**
     * ����ʱ��
     */
    private Date applyDate;
    /**
     * ѧ��״̬
     */
    private String rollState;
    /**
     * ����״̬�� 0δ�ύ 1�ύδ�ϱ� 2�ϱ�δ��� 3���δ���� 4 ����
     */
    private String handleState;

    public AbnormalResultObject(Long studentId, String cscId, String sex, String passportName, String applyUserName, String country, Date applyDate, String rollState, String handleState) {
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
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
        String resultSql = "select new gov.gwssi.csc.scms.domain.query.AbnormalResultObject(student.id, student.cscId, basicInfo.sex, " +
                "basicInfo.passportName, " +
                "basicInfo.country, schoolRoll.rollState,abnormal.handleState,abnormal.applyUserName,abnormal.applyDate) ";
        return resultSql;
    }
}
