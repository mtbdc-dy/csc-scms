package gov.gwssi.csc.scms.domain.user;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    /**
     * 角色菜单
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PUB_ROLE_MENU", joinColumns = {@JoinColumn(name = "ROLEID")},
            inverseJoinColumns = {@JoinColumn(name = "MENUID")})
    private List<Menu> menus = new ArrayList<Menu>();

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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
