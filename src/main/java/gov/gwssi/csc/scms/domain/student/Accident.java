package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * Created by Wang Rui on 2015/3/30.
 * 突发事件
 */
@Entity
@Table(name = "LHLX_ACCIDENT")
public class Accident {
    private String summary;
    private String happen_time;
    private String responsibility_type;
    private String happen_place;
    private String type;
    private String update_by;
    private String reason;
    private String processing_status;
    private java.sql.Date update_date;
    @Id
    private String id;

    @OneToOne
//    @JoinColumn(name="student_csc_id",insertable=true,unique=true)
    private Student student;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHappen_time() {
        return happen_time;
    }

    public void setHappen_time(String happen_time) {
        this.happen_time = happen_time;
    }

    public String getResponsibility_type() {
        return responsibility_type;
    }

    public void setResponsibility_type(String responsibility_type) {
        this.responsibility_type = responsibility_type;
    }

    public String getHappen_place() {
        return happen_place;
    }

    public void setHappen_place(String happen_place) {
        this.happen_place = happen_place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProcessing_status() {
        return processing_status;
    }

    public void setProcessing_status(String processing_status) {
        this.processing_status = processing_status;
    }

    public java.sql.Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(java.sql.Date update_date) {
        this.update_date = update_date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
