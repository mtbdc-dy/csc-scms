package gov.gwssi.csc.scms.domain.dynamicReport;

/**
 * Created by wangzishi on 15/9/28.
 */
public class OrderCondition {
    private String table;
    private String column;
    private String orderType;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}

