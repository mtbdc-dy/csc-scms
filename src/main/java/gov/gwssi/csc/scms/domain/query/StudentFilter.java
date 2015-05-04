package gov.gwssi.csc.scms.domain.query;

import gov.gwssi.csc.scms.domain.user.Right;
import gov.gwssi.csc.scms.domain.user.User;

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

    private String getConditionFilter(List<FilterCell> condition) {

        StringBuilder sb = new StringBuilder();

        for (FilterCell fc : condition) {
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

    public String getFilter(User user) {
        if (conditions == null || conditions.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(getConditionFilter(conditions));

        sb.append(getUserFilter(user));

        return sb.toString();

    }

    private String getUserFilter(User user) {
        //节点类型：1基金委；2驻外使（领）馆教育处（组）；3高等院校
        String nodeType = user.getNode().getNodeType();

        if ("1".equals(nodeType)) {
            StringBuilder sb = new StringBuilder();
            List<Right> rights = user.getRights();

            if (rights.size() == 0)
                return "";
            if (rights.size() == 1) {
                sb.append(" and schoolRoll.studentType = '").append(rights.get(0).getRegionId()).append("\' ");
            } else {
                StringBuilder tempRight = new StringBuilder();
                tempRight.append('(');
                for (Right right : rights) {
                    tempRight.append("'").append(right.getRegionId()).append("\'").append(",");
                }
                tempRight.setCharAt(tempRight.length() - 1, ')');

                sb.append(" and schoolRoll.studentType in ").append(tempRight);

                return sb.toString();
            }

        }
        if ("3".equals(nodeType)) {
            StringBuilder sb = new StringBuilder();
            sb.append(" and schoolRoll.currentUniversity = '").append(user.getNode().getNodeId()).append("' ");
            return sb.toString();
        }
        throw new RuntimeException("wrong value of the nodeType:" + nodeType);
    }
}
