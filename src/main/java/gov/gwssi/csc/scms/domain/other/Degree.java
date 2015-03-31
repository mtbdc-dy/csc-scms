package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_DEGREE")
public class Degree {
    @Id
    private Long id;
    private String update_by;
    private String english_name;
    private String enabled;
    private String degree_name;
    private Date update_date;
    private String degree_no;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getDegree_name() {
        return degree_name;
    }

    public void setDegree_name(String degree_name) {
        this.degree_name = degree_name;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getDegree_no() {
        return degree_no;
    }

    public void setDegree_no(String degree_no) {
        this.degree_no = degree_no;
    }
}
