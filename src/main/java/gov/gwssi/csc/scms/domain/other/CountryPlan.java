package gov.gwssi.csc.scms.domain.other;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_COUNTRY_PLAN")
public class CountryPlan {
    @Id
    private Long id;
    private BigDecimal jh;
    private String gb;
    private BigDecimal nd;
    private Date update_date;
    private String update_by;
    private String enabled;
    private String zb;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getJh() {
        return jh;
    }

    public void setJh(BigDecimal jh) {
        this.jh = jh;
    }

    public String getGb() {
        return gb;
    }

    public void setGb(String gb) {
        this.gb = gb;
    }

    public BigDecimal getNd() {
        return nd;
    }

    public void setNd(BigDecimal nd) {
        this.nd = nd;
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

    public String getZb() {
        return zb;
    }

    public void setZb(String zb) {
        this.zb = zb;
    }
}
