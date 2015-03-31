package gov.gwssi.csc.scms.domain.other;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_ACCIDENT_TYPE")
public class AccidentType {
    @Id
    private Long id;
    private Date update_date;
    private String accident_type_name;
    private String enabled;
    private String accident_type_no;
    private String update_by;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getAccident_type_name() {
        return accident_type_name;
    }

    public void setAccident_type_name(String accident_type_name) {
        this.accident_type_name = accident_type_name;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getAccident_type_no() {
        return accident_type_no;
    }

    public void setAccident_type_no(String accident_type_no) {
        this.accident_type_no = accident_type_no;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }
}