package gov.gwssi.csc.scms.domain.dynamicReport.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * Join 配置条件
 * Created by wangzishi on 15/9/28.
 */
@Entity
@Table(name = "SCMS_D_CFG_JOIN")
public class JoinCondition extends Condition {
    private String id;
    private Configuration config;
    private String table;
    private String joinType;

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

    @Column(name = "JOIN_TYPE")
    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }
}
