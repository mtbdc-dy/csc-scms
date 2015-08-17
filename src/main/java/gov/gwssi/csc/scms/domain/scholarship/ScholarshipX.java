package gov.gwssi.csc.scms.domain.scholarship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by gaochen 20150817.
 * 奖学金评审
 */
@Entity
@Table(name = "V_SCHOLARSHIP_LASTYEAR")
public class ScholarshipX {
    @Id
    //@Column(length = 19)
    private String id;

    @Column
    private String studentId;
    @Column
    private String cscId;
    @Column
    private String scholarshipId;
    @Column
    private String schReview;
    @Column
    private String cscReview;
    @Column
    private String schResult;
    @Column
    private String cscResult;
    @Column
    private String schReason;
    @Column
    private Date schStartTime;
    @Column
    private Date schEndTime;
    @Column
    private String cscReason;
    @Column
    private Date cscStartTime;
    @Column
    private Date cscEndTime;
    @Column
    private Long qualNum;
    @Column
    private Long unQualNum;
    @Column
    private String schoolSta;
    @Column
    private String cscSta;
    @Column
    private String cscrresult_lastyear;

    /**
     *年度
     */
    @Column
    private long year;
    /**
     *院校 DIM_UNIVERSITY代码
     */
    @Column
    private String school;





    /**
     *合格人数
     */
//    @Column(name = "qualNum",length=16)
//    private Long qualNum;

    /**
     *不合格人数
     */
//    @Column(name = "unQualNum",length=16)
//    private Long unQualNum;
//    /**
//     *创建人
//     */
//    @Column(name = "CREATEBY",length=20)
//    private String createBy;
//    /**
//     *入库日期
//     */
//    @Column(name = "CREATED")
//    private Date created;
//    /**
//     *修改人
//     */
//    @Column(name = "UPDATEBY",length=20)
//    private String updateBy;
//    /**
//     *修改时间
//     */
//    @Column(name = "UPDATED")
//    private Date updated;
    /**
     *基金委状态：0未提交；1提交
     */
//    @Column(name = "CSCSTA",length=1)
//    private String cscSta;
    /**
     *学校状态：0未提交；1提交
     */
//    @Column(name = "SCHOOLSTA",length=1)
//    private String schoolSta;
//    /**
//     *学校评审提交日期
//     */
//    @Column(name = "SCHOOLDATE")
//    private Date schoolDate;
//
//    /**
//     *基金委批复日期
//     */
//    @Column(name = "CSCDATE")
//    private Date cscDate;
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

    public String getSchoolSta() {
        return schoolSta;
    }

    public void setSchoolSta(String schoolSta) {
        this.schoolSta = schoolSta;
    }

    public String getCscSta() {
        return cscSta;
    }

    public void setCscSta(String cscSta) {
        this.cscSta = cscSta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    public String getCscReview() {
        return cscReview;
    }

    public void setCscReview(String cscReview) {
        this.cscReview = cscReview;
    }



    public String getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(String scholarshipId) {
        this.scholarshipId = scholarshipId;
    }



    public String getSchResult() {
        return schResult;
    }

    public void setSchResult(String schResult) {
        this.schResult = schResult;
    }

    public String getCscId() {
        return cscId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public String getSchReview() {
        return schReview;
    }

    public void setSchReview(String schReview) {
        this.schReview = schReview;
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

    public String getCscrresult_lastyear() {
        return cscrresult_lastyear;
    }

    public void setCscrresult_lastyear(String cscrresult_lastyear) {
        this.cscrresult_lastyear = cscrresult_lastyear;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
