package gov.gwssi.csc.scms.domain.user;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Murray on 2015/4/30.
 */
@Entity
@Table(name = "PUB_MENU")
public class Menu {
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
    private String parentId;
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
    private String ico;
    /**
     * 子菜单
     */
    @OneToMany(fetch = FetchType.EAGER)
    private List<Menu> children;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
