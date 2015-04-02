package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/3/30.
 * 突发事件
 */
@Entity
@Table(name = "SCMS_ACCIDENT")
public class Accident {
    @Id
    private String id;
    @ManyToOne
    private Student student;
    private String responsibility_type;//责任类别
    private String type;//类别
    private String reason;//原因
    private String happen_time;//发生时间
    private String happen_place;//发生地点
    private String handle_status;//处理状态
    private String summary;//情况摘要
    private Date update_date;//记录时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getResponsibility_type() {
        return responsibility_type;
    }

    public void setResponsibility_type(String responsibility_type) {
        this.responsibility_type = responsibility_type;
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

    public String getHappen_time() {
        return happen_time;
    }

    public void setHappen_time(String happen_time) {
        this.happen_time = happen_time;
    }

    public String getHappen_place() {
        return happen_place;
    }

    public void setHappen_place(String happen_place) {
        this.happen_place = happen_place;
    }

    public String getHandle_status() {
        return handle_status;
    }

    public void setHandle_status(String handle_status) {
        this.handle_status = handle_status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
