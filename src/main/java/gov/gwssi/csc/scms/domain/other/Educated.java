package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_EDUCATED")
public class Educated {
    @Id
    private Long id;
    private String update_by;
    private String educated_name;
    private String educated_no;
    private Date update_date;
    private String enabled;

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

    public String getEducated_name() {
        return educated_name;
    }

    public void setEducated_name(String educated_name) {
        this.educated_name = educated_name;
    }

    public String getEducated_no() {
        return educated_no;
    }

    public void setEducated_no(String educated_no) {
        this.educated_no = educated_no;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
