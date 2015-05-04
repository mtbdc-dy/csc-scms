package gov.gwssi.csc.scms.domain.query;

import gov.gwssi.csc.scms.domain.user.User;

import java.util.List;

/**
 * Created by lzs on 2015/4/29.
 */
public class AbnormalFilter implements Filter{
    private AbnormalFilterObject filterObject;
    private List<FilterCell> conditions;
    public AbnormalFilter(AbnormalFilterObject filterObject) {
        this.filterObject = filterObject;
        if (filterObject != null)
            conditions = filterObject.getConditions();
    }
    @Override
    public String getFilter(User user) {
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
