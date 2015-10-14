package gov.gwssi.csc.scms.domain.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lei on 2015/5/4.
 * 数据权限表
 */
@Entity
@Table(name = "DIM_PROJECT")
public class Project {

    public static final String ENABLED = "1";

    public static final String UNENABLED = "0";
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
    @ManyToOne
    @JoinColumn(name = "parentId")
    private Project parent;
    /**
     * 类型（前缀）：T  项目类别；U  项目名称。
     */
    private String type;
    /**
     * 有效标志：0不启用；1启用
     */
    private String enabled;
    /**
     *子节点
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Where(clause = "enabled = '1'")
    private List<Project> children;

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

    public Project getParent() {
        return parent;
    }

    public void setParent(Project parent) {
        this.parent = parent;
    }

    public List<Project> getChildren() {
        return children;
    }

    public void setChildren(List<Project> children) {
        this.children = children;
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
