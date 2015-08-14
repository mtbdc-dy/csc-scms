package gov.gwssi.csc.scms.domain.scholarship;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/14.
 * 奖学金评审明细表
 */
@Entity
@Table(name = "SCMS_SCHOLARSHIP_DETAIL")
public class ScholarshipDetail {
    @Id

    private String id;


    /**
     *评审结果(院校) 0不合格 1合格
     */
    @Column(name = "schReview", length = 1)
    private String schReview;//评审结果(院校)

    /**
     *处理结果（院校） 0中止 1取消
     */
    @Column(name = "schResult", length = 1)
    private String schResult;//处理结果（院校）

    /**
     *评审结果（基金委） 0不合格 1合格
     */
    @Column(name = "cscReview", length = 1)
    private String cscReview;//评审结果（基金委）

    /**
     *处理结果（基金委） 0中止 1取消 2 继续 3 恢复
     */
    private String cscResult;//处理结果（基金委）
    /**
     * 学校处理原因
     */
    private String schReason;//处理原因

    /**
     * 学校不合格时间起
     */
    private Date schStartTime;//不合格时间起

    /**
     * 学校不合格时间止
     */
    private Date schEndTime;//不合格时间止
    /**
     *基金委处理原因
     */
    private String cscReason;//处理原因

    /**
     *基金委不合格时间起
     */
    private Date cscStartTime;//不合格时间起

    /**
     *基金委不合格时间止
     */
    private Date cscEndTime;//不合格时间止

    /**
     * 主表id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SCHOLARSHIPID")
    private Scholarship scholarship;
//    /**
//     * 学生id
//     */
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "STUDENTID")
//    private Student student;

    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchReview() {
        return schReview;
    }

    public void setSchReview(String schReview) {
        this.schReview = schReview;
    }

    public String getSchResult() {
        return schResult;
    }

    public void setSchResult(String schResult) {
        this.schResult = schResult;
    }

    public String getCscReview() {
        return cscReview;
    }

    public void setCscReview(String cscReview) {
        this.cscReview = cscReview;
    }

    public String getCscrResult() {
        return cscResult;
    }

    public void setCscrResult(String cscrResult) {
        this.cscResult = cscrResult;
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

    public Scholarship getScholarship() {
        return scholarship;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
}
