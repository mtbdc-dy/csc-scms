package gov.gwssi.csc.scms.domain.codemaintenance;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by TianJ on 2016/12/23.
 */
@Entity
@Table(name = "v_dim_agency_codemaintanence")
public class CodemaintenanceAgency {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private String enabled;
    @Column
    private String fullname;
    @Column
    private Date updated;
    @Column
    private String tableen;
    @Column
    private String parentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getTableen() {
        return tableen;
    }

    public void setTableen(String tableen) {
        this.tableen = tableen;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
