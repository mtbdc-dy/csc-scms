package gov.gwssi.csc.scms.domain.log;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Murray on 2015/4/27.
 */
@Entity
@Table(name = "SCMS_OPT_LOG")
public class OperationLog {
    /**
     * id
     */
    private Long id;
    /**
     * 操作类型：
     * 1.新增，2.修改，3.删除
     */
    private String optType;
    /**
     * 菜单Id
     */
    private Long menuId;
    /**
     * 菜单名称：
     * 报道，注册，在校生学籍管理，自动任务
     */
    private String menu;
    /**
     * 学生id
     */
    private Long studentId;
    /**
     * 修改前内容
     */
    private String before;
    /**
     * 修改后内容
     */
    private String after;
    /**
     * 修改字段英文名
     */
    private String colunmEN;
    /**
     * 修改字段中文名
     */
    private String colunmCH;
    /**
     * 修改表英文名
     */
    private String tableEN;
    /**
     * 修改表中文名
     */
    private String tableCH;
    /**
     * 节点类型
     */
    private String nodeType;
    /**
     * 节点Id
     */
    private String nodeId;
    /**
     * 创建人ID
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date creatD;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getColunmEN() {
        return colunmEN;
    }

    public void setColunmEN(String colunmEN) {
        this.colunmEN = colunmEN;
    }

    public String getColunmCH() {
        return colunmCH;
    }

    public void setColunmCH(String colunmCH) {
        this.colunmCH = colunmCH;
    }

    public String getTableEN() {
        return tableEN;
    }

    public void setTableEN(String tableEN) {
        this.tableEN = tableEN;
    }

    public String getTableCH() {
        return tableCH;
    }

    public void setTableCH(String tableCH) {
        this.tableCH = tableCH;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreatD() {
        return creatD;
    }

    public void setCreatD(Date creatD) {
        this.creatD = creatD;
    }
}
