package gov.gwssi.csc.scms.domain.queryfilter;

import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public class StudentQueryFilter implements QueryFilter {

    private StudentFilterObject filterObject;

    private List<FilterCell> conditions;

    public StudentQueryFilter(StudentFilterObject filterObject) {
        this.filterObject = filterObject;
        if (filterObject != null)
            conditions = filterObject.getConditions();
    }

    @Override
    public String getQueryFilter() {
        if (conditions == null || conditions.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (FilterCell fc : conditions) {
            String str[] = fc.getValue().split(",");

            if ("String".equalsIgnoreCase(fc.getType())) {
                if (str.length > 1) {
                    sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" in (");
                    for (String parm : str) {
                        sb.append(parm).append(",");
                    }
                    sb.setCharAt(sb.length(), ')');
                } else {
                    sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" = '").append(str[0]).append("' ");
                }
            }
            if ("Date".equalsIgnoreCase(fc.getType())) {
                if (str.length > 1) {
                    sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" between (");
                    sb.append("to_date('").append(str[0]).append("','yyyy-mm-dd hh24:mi:ss'),").append("to_date('").append(str[1]).append("','yyyy-mm-dd hh24:mi:ss'))");
                } else {
                    sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" = ").append("to_date('").append(str[0]).append("','yyyy-mm-dd hh24:mi:ss')");
                }
            }
        }
        return sb.toString();
    }
}
