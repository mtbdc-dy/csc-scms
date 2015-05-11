package gov.gwssi.csc.scms.domain.query;

import java.util.Date;

/**
 * Created by lzs on 2015/5/5.
 * 学生列表供新增申请查询使用
 */
public class AddStudentResultObject  extends ResultObject{
    /**
     * 学生ID
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
     * 州别
     */
    private String continent;
    /**
     * 国籍
     */
    private String country;

    public AddStudentResultObject(String studentId, String cscId, String passportName, String continent, String country) {
        this.studentId = studentId;
        this.cscId = cscId;
        this.passportName = passportName;
        this.continent = continent;
        this.country = country;
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
    public static String getResultObject() {
        /**
         *  this.studentId = studentId;
         this.cscId = cscId;
         this.passportName = passportName;
         this.continent = continent;
         this.country = country;
         */
        String resultSql = "select new gov.gwssi.csc.scms.domain.query.AddStudentResultObject(student.id, student.cscId,  " +
                "basicInfo.passportName, " +
                "basicInfo.continent,basicInfo.country) ";
        return resultSql;
    }
}
