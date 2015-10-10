package gov.gwssi.csc.scms.domain.dynamicReport.Configuration;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by wangzishi on 15/9/28.
 */
@Entity
@Table(name = "SCMS_D_CFG_ORDER")
public class OrderCondition {
    private String id;
    private Configuration config;
    private String table;
    private String column;
    private String orderType;

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

    @Column(name = "ORDER_TYPE")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}

