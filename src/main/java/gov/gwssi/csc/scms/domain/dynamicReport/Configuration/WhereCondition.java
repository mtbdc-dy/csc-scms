package gov.gwssi.csc.scms.domain.dynamicReport.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.persistence.Column;

/**
 * where 配置条件
 * Created by wangzishi on 15/9/28.
 */
@Entity
@javax.persistence.Table(name = "SCMS_D_CFG_WHERE")
public class WhereCondition {
    private String id;
    private Configuration config;
    private String lParenthese;
    private String table;
    private String column;
    private String operator;
    private String condition;
    private String rParenthese;
    private String logic;

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

    @Column(name = "L_PRTS")
    @JsonProperty(value = "lParenthese")
    public String getLParenthese() {
        return lParenthese;
    }

    public void setLParenthese(String lParenthese) {
        this.lParenthese = lParenthese;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Column(name = "R_PRTS")
    @JsonProperty(value = "rParenthese")
    public String getRParenthese() {
        return rParenthese;
    }

    public void setRParenthese(String rParenthese) {
        this.rParenthese = rParenthese;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }
}
