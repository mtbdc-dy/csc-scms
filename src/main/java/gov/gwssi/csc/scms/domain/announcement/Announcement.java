package gov.gwssi.csc.scms.domain.announcement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by tianjing on 2015/8/7.
 */
@Entity
@Table(name = "SCMS_ANNOUNCEMENT")
public class Announcement {
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 主题
     */
    @Column(name = "THEME")
    private String theme;

    /**
     * 正文
     */
    @Column(name = "TEXT")
    private String text;

    /**
     * 发布人
     */
    @Column(name = "CREATEBY")
    private String createby;

    /**
     * 发布时间
     */
    @Column(name = "CREATED")
    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
