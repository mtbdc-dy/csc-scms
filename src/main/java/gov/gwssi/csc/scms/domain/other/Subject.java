package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_SUBJECT")
public class Subject {
    @Id
    private Long id;
    private String subject_no;
    private String subject_name;
    private String update_by;
    private String enabled;
    private Date update_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject_no() {
        return subject_no;
    }

    public void setSubject_no(String subject_no) {
        this.subject_no = subject_no;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}