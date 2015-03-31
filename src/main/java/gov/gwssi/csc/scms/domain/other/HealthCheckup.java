package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_HEALTH_CHECKUP")
public class HealthCheckup {
    @Id
    private Long id;
    private Date update_date;
    private String health_checkup_no;
    private String enabled;
    private String update_by;
    private String health_checkup_name;

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

    public String getHealth_checkup_no() {
        return health_checkup_no;
    }

    public void setHealth_checkup_no(String health_checkup_no) {
        this.health_checkup_no = health_checkup_no;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getHealth_checkup_name() {
        return health_checkup_name;
    }

    public void setHealth_checkup_name(String health_checkup_name) {
        this.health_checkup_name = health_checkup_name;
    }
}
