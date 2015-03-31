package gov.gwssi.csc.scms.domain.other;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_PUB_ROLE_DATA")
public class PubRoleData {
    @Id
    private Long id;
    private String role_id;
    private String region_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }
}
