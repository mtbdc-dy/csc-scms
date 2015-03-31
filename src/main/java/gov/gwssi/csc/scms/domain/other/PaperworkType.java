package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_PAPERWORK_TYPE")
public class PaperworkType {
    @Id
    private Long id;
    private String update_by;
    private Date update_date;
    private String enabled;
    private String paperwork_type_name;
    private String paperwork_type_no;

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

    public String getPaperwork_type_name() {
        return paperwork_type_name;
    }

    public void setPaperwork_type_name(String paperwork_type_name) {
        this.paperwork_type_name = paperwork_type_name;
    }

    public String getPaperwork_type_no() {
        return paperwork_type_no;
    }

    public void setPaperwork_type_no(String paperwork_type_no) {
        this.paperwork_type_no = paperwork_type_no;
    }
}
