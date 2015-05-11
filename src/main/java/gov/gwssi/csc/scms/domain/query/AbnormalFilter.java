package gov.gwssi.csc.scms.domain.query;

import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;

import java.util.List;

/**
 * Created by lzs on 2015/4/29.
 */
public class AbnormalFilter implements Filter {
    private AbnormalFilterObject filterObject;
    private List<FilterCell> conditions;

    public AbnormalFilter(AbnormalFilterObject filterObject) {
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
        //用户类别：1基金委；2学校；
        String userType = user.getUserType();
        if ("1".equals(userType)) {
            StringBuilder sb = new StringBuilder();
            List<Project> projects = user.getProjects();
            sb.append(" and abnormal.state in ('1','2','3','4')  ");
            if (projects.size() == 0)
                return "";
            if (projects.size() == 1) {
                sb.append(" and schoolRoll.studentType = '").append(projects.get(0).getProjectId()).append("\' ");
            } else {
                StringBuilder tempRight = new StringBuilder();
                tempRight.append('(');
                for (Project project : projects) {
                    tempRight.append("'").append(project.getProjectId()).append("\'").append(",");
                }
                tempRight.setCharAt(tempRight.length() - 1, ')');

                sb.append(" and schoolRoll.studentType in ").append(tempRight);

                return sb.toString();
            }

        }
        if ("2".equals(userType)) {
            StringBuilder sb = new StringBuilder();
            sb.append(" and schoolRoll.currentUniversity = '").append(user.getNode().getNodeId()).append("' ");
            return sb.toString();
        }
        throw new RuntimeException("wrong value of the nodeType:" + userType);
    }
}
