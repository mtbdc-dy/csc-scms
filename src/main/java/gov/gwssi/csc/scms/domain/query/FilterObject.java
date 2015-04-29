package gov.gwssi.csc.scms.domain.query;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public abstract class FilterObject {

    private String offSet = "0";

    private String pageSize = "200";

    public String getOffSet() {
        return offSet;
    }

    public void setOffSet(String offSet) {
        this.offSet = offSet;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    abstract List getConditions();

    protected List<FilterCell> addCondition(List<FilterCell> conditions, String tableName, String filedName, String type, String value) {
        if (isNull(value))
            return conditions;
        conditions.add(new FilterCell(tableName, filedName, type, value));
        return conditions;
    }

    protected List<FilterCell> addCondition(List<FilterCell> conditions, String tableName, String filedName, String type, String value1, String value2) {
        if (isNull(value1) || isNull(value2))
            return conditions;
        conditions.add(new FilterCell(tableName, filedName, type, value1 + "," + value2));
        return conditions;
    }

    protected boolean isNull(String value) {
        return ("null".equalsIgnoreCase(value) || StringUtils.isEmpty(value));
    }

}
