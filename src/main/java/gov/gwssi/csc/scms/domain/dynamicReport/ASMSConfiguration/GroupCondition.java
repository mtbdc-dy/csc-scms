package gov.gwssi.csc.scms.domain.dynamicReport.ASMSConfiguration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;
/**
 * Grpoup 配置条件
 * Created by wangzishi on 15/9/28.
 */
@Entity(name = "ASMSGroupCondition")
@Table(name = "ASMS_D_CFG_GROUP", schema = "ASMS")
public class GroupCondition extends Condition {
    private String id;
    private Configuration config;
    private String table;
    private String column;

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
}
