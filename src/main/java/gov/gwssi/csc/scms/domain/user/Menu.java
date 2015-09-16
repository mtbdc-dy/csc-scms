package gov.gwssi.csc.scms.domain.user;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Murray on 2015/4/30.
 * 菜单实体类
 */
@Entity
@Table(name = "PUB_MENU")
public class Menu {

    public final static String ROOT_LEVEL = "1";

    /**
     * 菜单代码
     */
    @Id
    private String menuId;
    /**
     * 菜单名称
     */
    private String menu;
    /**
     * 父菜单Id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    private Menu parent;
    /**
     * 菜单类型：1、一级菜单 ；2、二级菜单 ；3、三级菜单
     */
    private String menuType;
    /**
     * 菜单对应的模块名称
     */
    private String module;
    /**
     * 菜单对应的图标的名称，如flower
     */
    private String icon;
    /**
     * 子菜单
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Menu> children;
    /**
     * 角色关联
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PUB_ROLE_MENU", joinColumns = {@JoinColumn(name = "MENUID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLEID")})
    private List<Role> role;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
