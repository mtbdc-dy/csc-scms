package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**
 * 突发事件
 */
@Entity
@Table(name = "SCMS_ACCIDENT")
public class Accident {
    @Id
    private String id;
    /**
     * 责任类别
     */
    private String responsibilityType;
    /**
     * 类别
     */
    private String type;
    /**
     * 原因
     */
    private String reason;
    /**
     * 发生时间
     */
    private Date happenTime;
    /**
     * 发生地点
     */
    private String happenAddress;
    /**
     * 处理状态
     */
    private String state;
    /**
     * 情况摘要
     */
    private String summary;
    /**
     * 记录时间
     */
    private Date updateTime;
    /**
     * 学生id
     */
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Student.class)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponsibilityType() {
        return responsibilityType;
    }

    public void setResponsibilityType(String responsibilityType) {
        this.responsibilityType = responsibilityType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

    public String getHappenAddress() {
        return happenAddress;
    }

    public void setHappenAddress(String happenAddress) {
        this.happenAddress = happenAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
