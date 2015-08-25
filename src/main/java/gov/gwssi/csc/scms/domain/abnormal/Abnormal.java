package gov.gwssi.csc.scms.domain.abnormal;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/15.
 * 异动管理
 */
 @Entity
 @Table(name = "SCMS_ABNORMAL")
public class Abnormal {
    @Id
//    @SequenceGenerator(name = "SCMS_ABNORMAL_ID",sequenceName = "SCMS_ABNORMAL_SEQ",allocationSize = 1)
//    @GeneratedValue(generator = "SCMS_ABNORMAL_ID",strategy = GenerationType.SEQUENCE)
    private String id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid"/*,nullable = false*/,insertable = false, updatable = false)
    private Student student;
    /**
     * STUDENTID
     */
    private String studentId;
    /**
     *异动原因代码
     */
    @Column(name = "reasonId",length=6)
    private String reasonId;//异动原因代码
    /**
     *异动原因类别代码
     */
    @Column(name = "reasonTypeId",length=6)
    private String reasonTypeId;//异动原因类别代码
    /**
     *异动说明 20150204会议纪要 限制150字符、必填
     */
    @Column(name = "reason",length=300)
    private String reason;//异动说明

    /**
     *异动上传pdf文件路径
     */
    @Column(name = "applyUri",length=4000)
    private String applyUri;//异动上传pdf文件路径

    /**
     *审批结果 0不同意 1同意
     */
    @Column(name = "approResult",length=1)
    private String approResult;//审批结果

    /**
     *审批意见
     */
    @Column(name = "approOpinion",length=300)
    private String approOpinion;//审批意见

    /**
     *发文pdf文件路径
     */
    @Column(name = "publicUri",length=4000)
    private String publicUri;//发文pdf文件路径

    /**
     *申请人ID
     */
    @Column(name = "applyUserId",length=20)
    private String applyUserId;//申请人ID

    /**
     *申请人
     */
    @Column(name = "applyUserName",length=50)
    private String applyUserName;//申请人

    /**
     *申请日期
     */
    @Column(name = "applyTime")
    private Date applyTime;//申请日期

    /**
     *上报人ID
     */
    @Column(name = "reportUserId",length=20)
    private String reportUserId;//上报人ID
    /**
     *上报人
     */
    @Column(name = "reportUserName",length=50)
    private String reportUserName;//上报人

    /**
     *上报日期
     */
    @Column(name = "reportTime")
    private Date reportTime;//上报日期
    /**
     *审批人ID
     */
    @Column(name = "approUserId",length=20)
    private String approUserId;//审批人ID
    /**
     *审批人
     */
    @Column(name = "approUserName",length=50)
    private String approUserName;//审批人
    /**
     *审批日期
     */
    @Column(name = "approTime")
    private Date approTime;//审批日期
    /**
     *处理人ID
     */
    @Column(name = "handleUserId",length=20)
    private String handleUserId;//处理人ID
    /**
     *处理人
     */
    @Column(name = "handleUserName",length=50)
    private String handleUserName;//处理人
    /**
     *处理日期
     */
    @Column(name = "handleTime")
    private Date handleTime;//处理日期
    /**
     *处理状态
     *  0未提交 1提交未上报 2上报未审核 3审核未处理 4 处理
     */
    @Column(name = "state",length=1)
    private String state;//处理状态 0未提交 1提交未上报 2上报未审核 3审核未处理 4 处理
    /**
     *修改人
     */
    @Column(name = "updateBy",length=20)
    private String updateBy;

    /**
     *修改时间
     */
    @Column(name = "updated")
    private Date updated;

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

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonTypeId() {
        return reasonTypeId;
    }

    public void setReasonTypeId(String reasonTypeId) {
        this.reasonTypeId = reasonTypeId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyUri() {
        return applyUri;
    }

    public void setApplyUri(String applyUri) {
        this.applyUri = applyUri;
    }

    public String getApproResult() {
        return approResult;
    }

    public void setApproResult(String approResult) {
        this.approResult = approResult;
    }

    public String getApproOpinion() {
        return approOpinion;
    }

    public void setApproOpinion(String approOpinion) {
        this.approOpinion = approOpinion;
    }

    public String getPublicUri() {
        return publicUri;
    }

    public void setPublicUri(String publicUri) {
        this.publicUri = publicUri;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(String reportUserId) {
        this.reportUserId = reportUserId;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getApproUserId() {
        return approUserId;
    }

    public void setApproUserId(String approUserId) {
        this.approUserId = approUserId;
    }

    public String getApproUserName() {
        return approUserName;
    }

    public void setApproUserName(String approUserName) {
        this.approUserName = approUserName;
    }

    public Date getApproTime() {
        return approTime;
    }

    public void setApproTime(Date approTime) {
        this.approTime = approTime;
    }

    public String getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(String handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getHandleUserName() {
        return handleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        this.handleUserName = handleUserName;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
