package gov.gwssi.csc.scms.domain.other;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_ACCIDENT_STATE")
public class AccidentState {
    @Id
    private Long id;
    private Date update_date;
    private String update_by;
    private String enabled;
    private String accident_state_name;
    private String accident_state;

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

    public String getAccident_state_name() {
        return accident_state_name;
    }

    public void setAccident_state_name(String accident_state_name) {
        this.accident_state_name = accident_state_name;
    }

    public String getAccident_state() {
        return accident_state;
    }

    public void setAccident_state(String accident_state) {
        this.accident_state = accident_state;
    }
}
