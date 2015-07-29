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
    @Column(name = "cscRresult", length = 1)
    private String cscRresult;//处理结果（基金委）

    /**
     *处理原因
     */
    @Column(name = "reason", length = 300)
    private String reason;//处理原因

    /**
     *不合格时间起
     */
    @Column(name = "startTime")
    private Date startTime;//不合格时间起

    /**
     *不合格时间止
     */
    @Column(name = "endTime")
    private Date endTime;//不合格时间止

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

    public String getCscRresult() {
        return cscRresult;
    }

    public void setCscRresult(String cscRresult) {
        this.cscRresult = cscRresult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
