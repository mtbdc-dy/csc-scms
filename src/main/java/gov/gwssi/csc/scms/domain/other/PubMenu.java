package gov.gwssi.csc.scms.domain.other;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_PUB_MENU")
public class PubMenu {
    @Id
    private String group_id;
    private String image_url;
    private String controller_url;
    private String window_heigth;
    private String window_width;
    private String menu_id;
    private String scrollbar;
    private String link_type;
    private String system_id;
    private String menu_name;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getController_url() {
        return controller_url;
    }

    public void setController_url(String controller_url) {
        this.controller_url = controller_url;
    }

    public String getWindow_heigth() {
        return window_heigth;
    }

    public void setWindow_heigth(String window_heigth) {
        this.window_heigth = window_heigth;
    }

    public String getWindow_width() {
        return window_width;
    }

    public void setWindow_width(String window_width) {
        this.window_width = window_width;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getScrollbar() {
        return scrollbar;
    }

    public void setScrollbar(String scrollbar) {
        this.scrollbar = scrollbar;
    }

    public String getLink_type() {
        return link_type;
    }

    public void setLink_type(String link_type) {
        this.link_type = link_type;
    }

    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
}
