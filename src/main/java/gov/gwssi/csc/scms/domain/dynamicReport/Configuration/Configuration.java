package gov.gwssi.csc.scms.domain.dynamicReport.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.*;

/**
 * 动态报表配置
 * Created by wangzishi on 15/10/9.
 */
@Entity
@Table(name = "SCMS_D_CFG")
@NamedStoredProcedureQueries(value = {
        @NamedStoredProcedureQuery(name = "Configuration.newId", procedureName = "P_SCMS_GEN_ID", parameters = {
                @StoredProcedureParameter(name = "seqName", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "id", mode = ParameterMode.OUT, type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "Configuration.generateStatisticsSQL", procedureName = "P_SCMS_D_STATISTICS", parameters = {
                @StoredProcedureParameter(name = "configId", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "sql", mode = ParameterMode.OUT, type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "Configuration.generateQuerySQL", procedureName = "P_SCMS_D_QUERY", parameters = {
                @StoredProcedureParameter(name = "configId", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "sql", mode = ParameterMode.OUT, type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "Configuration.test", procedureName = "PCK_SCMS_JOB.P_TEST", parameters = {
                @StoredProcedureParameter(name = "in", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "out", mode = ParameterMode.OUT, type = List.class)
        })
})
public class Configuration implements Serializable {
    private String id;
    private String title;
    private String reportType;
    private String description;
    private String accessState;
    private String rawConfig;
    private String sql;
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

    public Configuration() {
    }

    public Configuration(Configuration configuration) {
        this.id = configuration.getId();
        this.title = configuration.getTitle();
        this.description = configuration.getDescription();
        this.accessState = configuration.getAccessState();
        this.reportType = configuration.getReportType();
        this.rawConfig = configuration.getRawConfig();
        this.sql = configuration.getSql();
        this.created = configuration.getCreated();
        this.createBy = configuration.getCreateBy();
        this.updated = configuration.getUpdated();
        this.updateBy = configuration.getUpdateBy();
    }

    @Transient
    @JsonIgnore
    public List<Cell> getOrderedCells() {
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

//    @Transient
    @Column(name = "REPORT_TYPE")
    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
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

    @Lob
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "config", cascade = CascadeType.REMOVE)
    public Set<JoinCondition> getJoinConditions() {
        return joinConditions;
    }

    @JsonProperty
    public void setJoinConditions(Set<JoinCondition> joinConditions) {
        this.joinConditions = joinConditions;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "config", cascade = CascadeType.REMOVE)
    public List<WhereCondition> getWhereConditions() {
        return whereConditions;
    }

    @JsonProperty
    public void setWhereConditions(List<WhereCondition> whereConditions) {
        this.whereConditions = whereConditions;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "config", cascade = CascadeType.REMOVE)
    public Set<GroupCondition> getGroupConditions() {
        return groupConditions;
    }

    @JsonProperty
    public void setGroupConditions(Set<GroupCondition> groupConditions) {
        this.groupConditions = groupConditions;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "config", cascade = CascadeType.REMOVE)
    public Set<OrderCondition> getOrderConditions() {
        return orderConditions;
    }

    @JsonProperty
    public void setOrderConditions(Set<OrderCondition> orderConditions) {
        this.orderConditions = orderConditions;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "config", cascade = CascadeType.REMOVE)
    public List<SelectCondition> getSelectConditions() {
        return selectConditions;
    }

    @JsonProperty
    public void setSelectConditions(List<SelectCondition> selectConditions) {
        this.selectConditions = selectConditions;
    }

    @JsonIgnore
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