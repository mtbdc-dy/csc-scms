package gov.gwssi.csc.scms.domain.warning;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/16.
 */
@Entity
@Table(name = "SCMS_WARNING")
public class Warning {
    @Id
//    @SequenceGenerator(name = "SCMS_WARNING_ID",sequenceName = "SCMS_WARNING_SEQ",allocationSize = 1)
//    @GeneratedValue(generator = "SCMS_WARNING_ID",strategy = GenerationType.SEQUENCE)
    private String id;


    /**
     *加入预警名单原因
     */
    @Column(name = "addReason",length=300)
    private String addReason;
    /**
     *加入时间
     */
    @Column(name = "addTime")
    private Date addTime;

    /**
     *加入用户ID
     */
    @Column(name = "addUserId",length=20)
    private String addUserId;

    /**
     *加入用户名
     */
    @Column(name = "addUserName",length=50)
    private String addUserName;



    /**
     *移除预警名单原因
     */
    @Column(name = "rmReason",length=300)
    private String rmReason;
    /**
     *移除时间
     */
    @Column(name = "rmTime")
    private Date rmTime;

    /**
     *移除用户ID
     */
    @Column(name = "rmUserId",length=20)
    private String rmUserId;

    /**
     *移除用户名
     */
    @Column(name = "rmUserName",length=50)
    private String rmUserName;
    /**
     *创建人
     */
    @Column(name = "CREATEBY",length=50)
    private String createBy;
    /**
     *入库日期
     */
    @Column(name = "CREATED")
    private String created;
    /**
     *修改人
     */
    @Column(name = "UPDATEBY",length=50)
    private String updateBy;
    /**
     *修改时间
     */
    @Column(name = "UPDATED")
    private String updated;
    /**
     * 学生
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddReason() {
        return addReason;
    }

    public void setAddReason(String addReason) {
        this.addReason = addReason;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(String addUserId) {
        this.addUserId = addUserId;
    }

    public String getAddUserName() {
        return addUserName;
    }

    public void setAddUserName(String addUserName) {
        this.addUserName = addUserName;
    }

    public String getRmReason() {
        return rmReason;
    }

    public void setRmReason(String rmReason) {
        this.rmReason = rmReason;
    }

    public Date getRmTime() {
        return rmTime;
    }

    public void setRmTime(Date rmTime) {
        this.rmTime = rmTime;
    }

    public String getRmUserId() {
        return rmUserId;
    }

    public void setRmUserId(String rmUserId) {
        this.rmUserId = rmUserId;
    }

    public String getRmUserName() {
        return rmUserName;
    }

    public void setRmUserName(String rmUserName) {
        this.rmUserName = rmUserName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
