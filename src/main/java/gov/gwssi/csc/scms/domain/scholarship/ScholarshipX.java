package gov.gwssi.csc.scms.domain.scholarship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/14.
 * 奖学金评审
 */
@Entity
@Table(name = "v_scholarship_lastyear")
public class ScholarshipX {
    @Id
    //@Column(length = 19)
    private String id;

    @Column
    private String studentId;
    @Column
    private String scholarshipId;
    @Column
    private String schReview;
    @Column
    private String cscReview;
    @Column
    private String schResult;
    @Column
    private String cscrResult;
    @Column
    private String reason;
    @Column
    private Date startTime;
    @Column
    private Date endTime;
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
    @Column(name = "year",length=4)
    private long year;
    /**
     *院校 DIM_UNIVERSITY代码
     */
    @Column(name = "school",length=6)
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

    public String getCscrResult() {
        return cscrResult;
    }

    public void setCscrResult(String cscrResult) {
        this.cscrResult = cscrResult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getSchReview() {
        return schReview;
    }

    public void setSchReview(String schReview) {
        this.schReview = schReview;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
