package gov.gwssi.csc.scms.domain.query;

/**
 * Created by Murray on 2015/4/2.
 * 查询条件的最小单元，包含该查询条件的基本信息
 */
public class FilterCell {

    private String tableName;
    private String columnName;
    private String Type;
    private String value;

    public FilterCell(String tableName, String columnName, String type, String value) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.Type = type;
        this.value = value;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getType() {
        return Type;
    }

    public String getValue() {
        return value;
    }
}
