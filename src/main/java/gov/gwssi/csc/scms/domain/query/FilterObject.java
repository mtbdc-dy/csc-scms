package gov.gwssi.csc.scms.domain.query;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public abstract class FilterObject {


    public static final int OFFSETDEFULT = 0;//始值
    public static final int PAGESIZEDEFULT = 200;//页值


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
