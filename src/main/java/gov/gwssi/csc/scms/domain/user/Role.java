package gov.gwssi.csc.scms.domain.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/4/30.
 */
@Entity
@Table(name = "PUB_ROLE")
public class Role {
    /**
     * 角色代码
     */
	@Id
    private String roleId;
    /**
     * 角色名称
     */
    private String role;
    /**
     * 身份ID
     */
    private String identity;
    /**
     * 有效标识：0 无效 ，1 有效
     */
    private String enable;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
