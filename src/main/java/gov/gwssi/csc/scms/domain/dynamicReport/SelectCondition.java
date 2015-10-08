package gov.gwssi.csc.scms.domain.dynamicReport;

/**
 * Created by wangzishi on 15/10/8.
 */
public class SelectCondition {
    private String table;
    private String column;
    private String calculateType;
    private Integer level;
    private Boolean sumColumn;

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

    public String getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(String calculateType) {
        this.calculateType = calculateType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getSumColumn() {
        return sumColumn;
    }

    public void setSumColumn(Boolean sumColumn) {
        this.sumColumn = sumColumn;
    }
}
