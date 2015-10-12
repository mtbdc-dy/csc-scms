package gov.gwssi.csc.scms.domain.dynamicReport.Configuration;

import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.*;

/**
 * 动态报表配置
 * Created by wangzishi on 15/10/9.
 */
@Entity
@Table(name = "SCMS_D_CFG")
@NamedStoredProcedureQuery(name = "Configuration.newId", procedureName = "P_SCMS_GEN_ID", parameters = {
        @StoredProcedureParameter(name = "seqName", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "id", mode = ParameterMode.OUT, type = String.class)
})
public class Configuration {
    private String id;
    private String title;
    private String description;
    private String accessState;
    private String rawConfig;
    private Set<JoinCondition> joinConditions;
    private List<WhereCondition> whereConditions;
    private Set<GroupCondition> groupConditions;
    private Set<OrderCondition> orderConditions;
    private List<SelectCondition> selectConditions;
    private List<Cell> cells;
    private Calendar created;
    private String createBy;
    private Calendar updated;
    private String updateBy;

    @Transient
    public List<Cell> getOrderedCells(){
        List<Cell> cells = this.cells;
        Collections.sort(cells);
        return cells;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "CLOB")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ACCESS_TYPE")
    public String getAccessState() {
        return accessState;
    }

    public void setAccessState(String access) {
        this.accessState = access;
    }

    @Column(name = "RAW_CFG", columnDefinition = "CLOB")
    public String getRawConfig() {
        return rawConfig;
    }

    public void setRawConfig(String rawConfig) {
        this.rawConfig = rawConfig;
    }

    @OneToMany(mappedBy = "config")
    public Set<JoinCondition> getJoinConditions() {
        return joinConditions;
    }

    public void setJoinConditions(Set<JoinCondition> joinConditions) {
        this.joinConditions = joinConditions;
    }

    @OneToMany(mappedBy = "config")
    public List<WhereCondition> getWhereConditions() {
        return whereConditions;
    }

    public void setWhereConditions(List<WhereCondition> whereConditions) {
        this.whereConditions = whereConditions;
    }

    @OneToMany(mappedBy = "config")
    public Set<GroupCondition> getGroupConditions() {
        return groupConditions;
    }

    public void setGroupConditions(Set<GroupCondition> groupConditions) {
        this.groupConditions = groupConditions;
    }

    @OneToMany(mappedBy = "config")
    public Set<OrderCondition> getOrderConditions() {
        return orderConditions;
    }

    public void setOrderConditions(Set<OrderCondition> orderConditions) {
        this.orderConditions = orderConditions;
    }

    @OneToMany(mappedBy = "config")
    public List<SelectCondition> getSelectConditions() {
        return selectConditions;
    }

    public void setSelectConditions(List<SelectCondition> selectConditions) {
        this.selectConditions = selectConditions;
    }

    @OneToMany(mappedBy = "config", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}