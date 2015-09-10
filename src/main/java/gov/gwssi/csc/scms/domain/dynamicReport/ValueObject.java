package gov.gwssi.csc.scms.domain.dynamicReport;

/**
 * Created by WangZhenghua on 2015/5/29.
 * tables.json返回的value对象的值
 */
public class ValueObject {
    private String name;
    private String tableType;
    private String dataType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
