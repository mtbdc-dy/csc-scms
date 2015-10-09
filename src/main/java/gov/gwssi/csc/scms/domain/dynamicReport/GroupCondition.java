package gov.gwssi.csc.scms.domain.dynamicReport;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;
/**
 * Grpoup 配置条件
 * Created by wangzishi on 15/9/28.
 */
@Entity
@Table(name = "SCMS_D_CFG_GROUP")
public class GroupCondition {
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
