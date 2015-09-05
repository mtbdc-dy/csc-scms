package gov.gwssi.csc.scms.domain.dynamicReport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by WangZhenghua on 2015/5/13.
 * 动态统计报表配置
 */

@Entity
@javax.persistence.Table(name = "SCMS_D_CONFIG")
public class ReportConfiguration implements Serializable {
    private String id;
    // 配置类型(1查询、2统计)
    private String configType;
    // 标题
    private String title;
    // 描述
    private String describe;
    // 使用性质(1公用、2自用)
    private String accessState;
    // 发布状态(Y已发布、N未发布)
    private String publishState;
    // 发布时间
    private Date time;
    // 纸张样式(A4、B5等)
    private String paperSize;
    // 页边距上
    private String pageUp;
    // 页边距下
    private String pageDown;
    // 页边距左
    private String pageLeft;
    // 页边距右
    private String pageRight;
    // 竖向打印标志(Y竖向打印、N横向打印)
    private String print;
    // 交替色显示标志(Y交替显示、N非交替显示)
    private String color;
    // 列最小宽度
    private String minWidth;
    // sql语句
    private String sql;
    // 操作员代码
    private String createBy;
    // 操作员名称
    private String createByName;
    // 创建时间
    private Date created;

    public ReportConfiguration() {
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAccessState() {
        return accessState;
    }

    public void setAccessState(String accessState) {
        this.accessState = accessState;
    }

    public String getPublishState() {
        return publishState;
    }

    public void setPublishState(String publishState) {
        this.publishState = publishState;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    public String getPageUp() {
        return pageUp;
    }

    public void setPageUp(String pageUp) {
        this.pageUp = pageUp;
    }

    public String getPageDown() {
        return pageDown;
    }

    public void setPageDown(String pageDown) {
        this.pageDown = pageDown;
    }

    public String getPageLeft() {
        return pageLeft;
    }

    public void setPageLeft(String pageLeft) {
        this.pageLeft = pageLeft;
    }

    public String getPageRight() {
        return pageRight;
    }

    public void setPageRight(String pageRight) {
        this.pageRight = pageRight;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(String minWidth) {
        this.minWidth = minWidth;
    }

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @javax.persistence.Column(name = "SQL", columnDefinition = "CLOB")
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
