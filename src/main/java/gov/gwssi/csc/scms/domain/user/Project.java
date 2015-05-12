package gov.gwssi.csc.scms.domain.user;

import javax.persistence.*;

/**
 * Created by Lei on 2015/5/4.
 * 数据权限表
 */
@Entity
@Table(name = "DIM_PROJECT")
public class Project {
    /**
     * 项目Id
     */
    @Id
    private String projectId;
    /**
     * 项目名称（英文）
     */
    private String nameEN;
    /**
     * 项目名称（汉语）
     */
    private String nameCH;
    /**
     * 上级节点
     */
    private String parentId;
    /**
     * 类型（前缀）：T  项目类别；U  项目名称。
     */
    private String type;
    /**
     * 有效标志：0不启用；1启用
     */
    private String enabled;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameCH() {
        return nameCH;
    }

    public void setNameCH(String nameCH) {
        this.nameCH = nameCH;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
