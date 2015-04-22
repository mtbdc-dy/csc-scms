package gov.gwssi.csc.scms.domain.query;

import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public class StudentFilter implements Filter {

    private StudentFilterObject filterObject;

    private List<FilterCell> conditions;

    public StudentFilter(StudentFilterObject filterObject) {
        this.filterObject = filterObject;
        if (filterObject != null)
            conditions = filterObject.getConditions();
    }

    public String getFilter() {
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
                        sb.append("'").append(parm).append("',");
                    }
                    sb.setCharAt(sb.length(), ')');
                } else {
                    sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" = '").append(str[0]).append("' ");
                }
            }
            if ("Date".equalsIgnoreCase(fc.getType())) {
                if (str.length > 1) {
                    sb.append(" and to_char(").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(",'yyyy-mm-dd hh24:mi:ss') between ");
                    sb.append("'").append(str[0]).append("' and '").append(str[1]).append("' ");
                } else {
                    sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" = ").append("to_date('").append(str[0]).append("','yyyy-mm-dd hh24:mi:ss')");
                }
            }
        }
        return sb.toString();
    }
}
