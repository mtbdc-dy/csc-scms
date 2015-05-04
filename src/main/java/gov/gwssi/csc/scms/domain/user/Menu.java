package gov.gwssi.csc.scms.domain.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
     * 系统代码
     */
    private String systemId;
    /**
     * 链接open窗口的方式，function为调函数，self为自身窗口更新
     */
    private String linkType;
    /**
     * 弹窗宽度
     */
    private String winWidth;
    /**
     * 弹窗高度
     */
    private String winHeight;
    /**
     * 弹窗是否显示滚动条
     */
    private String scrollBar;
    /**
     * 图片名称
     */
    private String imageUrl;
    /**
     * 类别代码，菜单需要将同类的用分隔线分隔开
     */
    private String groupId;

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

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getWinWidth() {
        return winWidth;
    }

    public void setWinWidth(String winWidth) {
        this.winWidth = winWidth;
    }

    public String getWinHeight() {
        return winHeight;
    }

    public void setWinHeight(String winHeight) {
        this.winHeight = winHeight;
    }

    public String getScrollBar() {
        return scrollBar;
    }

    public void setScrollBar(String scrollBar) {
        this.scrollBar = scrollBar;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
