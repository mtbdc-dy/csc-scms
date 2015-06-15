package gov.gwssi.csc.scms.domain.log;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Id
    private String id;
    /**
     * 操作类型：
     * 1.新增，2.修改，3.删除
     */
    private String optType;
    /**
     * 业务模块代码
     */
    private String moduleId;
    /**
     * 业务模块
     */
    private String module;
    /**
     * 学生id
     */
    private String studentId;
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
    private String columnEN;
    /**
     * 修改字段中文名
     */
    private String columnCH;
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
    private Date createD;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
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

    public String getColumnEN() {
        return columnEN;
    }

    public void setColumnEN(String columnEN) {
        this.columnEN = columnEN;
    }

    public String getColumnCH() {
        return columnCH;
    }

    public void setColumnCH(String columnCH) {
        this.columnCH = columnCH;
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

    public Date getCreateD() {
        return createD;
    }

    public void setCreateD(Date createD) {
        this.createD = createD;
    }
}
