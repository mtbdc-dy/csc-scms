package gov.gwssi.csc.scms.domain.codemaintenance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Entity
@Table(name = "v_dim_pro_1_codemaintanence")
public class CodemaintanenceProjectFirst {
    @Id
    private String id;

    @Column
    private String name;

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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

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
}
