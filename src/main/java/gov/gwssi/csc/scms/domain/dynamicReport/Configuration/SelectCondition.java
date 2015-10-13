package gov.gwssi.csc.scms.domain.dynamicReport.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Select 配置条件
 * Created by wangzishi on 15/10/8.
 */
@Entity
@Table(name = "SCMS_D_CFG_SELECT")
public class SelectCondition extends Condition {
    private String id;
    private Configuration config;
    private String table;
    private String column;
    private String calculateType;
    private Integer level;
    private Boolean sumColumn;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "CFG_ID")
    @JsonIgnore
    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    @Column(name = "TABLE_ID")
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Column(name = "COLUMN_ID")
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Column(name = "CALCULATE_TYPE")
    public String getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(String calculateType) {
        this.calculateType = calculateType;
    }

    @Column(name = "LVL")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "SUMABLE", columnDefinition = "VARCHAR2(1)")
    public Boolean getSumColumn() {
        return sumColumn;
    }

    public void setSumColumn(Boolean sumColumn) {
        this.sumColumn = sumColumn;
    }
}
