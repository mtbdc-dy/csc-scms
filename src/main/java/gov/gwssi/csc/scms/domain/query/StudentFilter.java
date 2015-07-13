package gov.gwssi.csc.scms.domain.query;

import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;

import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public class StudentFilter implements Filter {
    //新生注册条件 是否报到不为“是” 且报到状态不为“报到”
    public static final String UNREGISTERED_STUDENT_CONDITION
            = " and schoolRoll.registed != 'AX0002' and schoolRoll.registerState !='AW0001'";
    //新生注册条件
    /*春季，即1.1（当年）<系统时间<6.30（当年）：
      1.1（当年）<汉补开始时间<6.30（当年），取汉补院校（或）
      1.1（当年）<专业开始时间<6.30（当年），取专业院校
     秋季，即7.1（当年）<系统时间<12.31（当年）：
      7.1（当年）<汉补开始时间<12.31（当年），取汉补院校（或）
     7.1（当年）<专业开始时间<12.31（当年），取专业院校
    */
    public static final StringBuffer FRESH_STUDENT_CONDITION = new StringBuffer(" and ((sysdate >= TO_DATE(extract(year from sysdate) || '-01-01','yyyy-mm-dd') and" +
        " sysdate < TO_DATE(extract(year from sysdate) || '-07-01','yyyy-mm-dd') and" +
        " ((schoolRoll.cramDateBegin >=" +
        "    TO_DATE(extract(year from sysdate) || '-01-01','yyyy-mm-dd') and" +
        " schoolRoll.cramDateBegin <" +
        "    TO_DATE(extract(year from sysdate) || '-07-01','yyyy-mm-dd')) or" +
        " (schoolRoll.majorStartDate >=" +
        "    TO_DATE(extract(year from sysdate) || '-01-01','yyyy-mm-dd') and" +
        " schoolRoll.majorStartDate <" +
        "    TO_DATE(extract(year from sysdate) || '-07-01','yyyy-mm-dd')))) or" +
        " (sysdate >= TO_DATE(extract(year from sysdate) || '-07-01','yyyy-mm-dd') and" +
        " sysdate <= add_months(trunc(sysdate,'yyyy'),12) and" +
        " ((cast(schoolRoll.cramDateBegin as date) >=" +
        "    TO_DATE(extract(year from sysdate) || '-07-01','yyyy-mm-dd') and" +
        " cast(schoolRoll.cramDateBegin as date) <=" +
        "    add_months(trunc(sysdate,'yyyy'),12)) or" +
        " (cast(schoolRoll.majorStartDate as date) >=" +
        "    TO_DATE(extract(year from sysdate) || '-07-01','yyyy-mm-dd') and" +
        " cast(schoolRoll.majorStartDate as date) <=" +
        "    add_months(trunc(sysdate,'yyyy'),12)))))");
    //在校生条件 是否报到为“是”，是否离华不为“是” 且报到年度不为当年 wangrui
    public static final String INTERNAL_STUDENT_CONDITION
            = " and schoolRoll.registed = 'AX0002' and schoolRoll.leaveChina!='BA0002' and REGISTERYEAR <>extract(year from sysdate) ";
//离华时间 离华时间为当年1月1日-8月31日之间
public static final String LEAVEDATA_STUDENT_CONDITION = " and sysdate >= TO_DATE(extract(year from sysdate) || '-01-01','yyyy-mm-dd') and sysdate < TO_DATE(extract(year from sysdate) || '-09-01','yyyy-mm-dd') ";
    private StudentFilterObject filterObject;

    private List<FilterCell> conditions;

    public StudentFilter(StudentFilterObject filterObject) {
        this.filterObject = filterObject;
        if (filterObject != null)
            conditions = filterObject.getConditions();
    }

    private String getConditionFilter(List<FilterCell> condition,String modleType,String userTpye) {

        StringBuilder sb = new StringBuilder();

        for (FilterCell fc : condition) {
            String str[] = fc.getValue().split(",");
if(fc.getType().indexOf("String")==-1&&"ticket".equals(modleType)){

        if("2".equals(userTpye)){//1 基金委用户 2学校用户

                sb.append(" and ").append("ticket").append(".").append("state").append(" in (");
                sb.append("'AT0001','AT0002','AT0005','AT0003','AT0003')");

        }else if("1".equals(userTpye)){

                sb.append(" and ").append("ticket").append(".").append("state").append(" in (");
                sb.append("'AT0002','AT0005','AT0003','AT0003')");

        }
//                        if(str[]){
//
//                        }

}
            if ("String".equalsIgnoreCase(fc.getType())) {
                if (str.length > 1) {
                    sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" in (");
                    for (String parm : str) {
                        sb.append("'").append(parm).append("',");
                    }
                    sb.setCharAt(sb.length(), ')');
                }else {
                    if("ticket".equals(modleType)){
                        if("2".equals(userTpye)){


                        if(!"ticket".equals(fc.getTableName())){
                            sb.append(" and ").append("ticket").append(".").append("state").append(" in (");
                            sb.append("'AT0001','AT0002','AT0005','AT0003','AT0004')");
                        }else{
                            if("AT0006".equals(fc.getValue())){
                                sb.append(" and ").append("ticket").append(".").append("state").append(" in (");
                                sb.append("'AT0002','AT0005')");
                            }
                        }
                        }else if("1".equals(userTpye)){
                            if(!"ticket".equals(fc.getTableName())){
                                sb.append(" and ").append("ticket").append(".").append("state").append(" in (");
                                sb.append("'AT0002','AT0005','AT0003','AT0004')");
                            }
                        }

                    }else{
                        sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" = '").append(str[0]).append("' ");
                        }
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
            //单独处理mode字段-参数待定
            if ("mode".equalsIgnoreCase(fc.getType())) {
                if ("".equals(str[0])) {

                }else if("abnormal".equals(str[0])){
                   //sb.append(" and ").append(fc.getTableName()).append(".").append(fc.getColumnName()).append(" = '").append(str[0]).append("' ");
               }else if("freshregister".equals(str[0])){//新生注册 日期条件和报到状态为“否”
                   sb.append(UNREGISTERED_STUDENT_CONDITION); //是否报到!=“是”
                   sb.append(FRESH_STUDENT_CONDITION);
               }else if("oldregister".equals(str[0])){//老生注册 日期条件和报到状态为“是”，是否离华为“否”
                   sb.append(INTERNAL_STUDENT_CONDITION);
//                   sb.append();
               }else if("ticket".equals(str[0])){//机票管理时间区间段 当年1月1号至8月31号
                   sb.append(LEAVEDATA_STUDENT_CONDITION);
               }else if("leavechina".equals(str[0])){//离华管理 默认条件未离华leaveChina !='BA0002'
                   sb.append(" and schoolRoll.leaveChina !='BA0002'");
               }
            }
        }
        return sb.toString();
    }

    public String getFilter(User user,String modleType,String userTpye) {
        if (conditions == null || conditions.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(getConditionFilter(conditions,modleType,userTpye));

        sb.append(getUserFilter(user));

        return sb.toString();

    }

    public String getUserFilter(User user) {
        //用户类别：1基金委；2学校；
        String userType = user.getUserType();

        if ("1".equals(userType)) {
            StringBuilder sb = new StringBuilder();
            List<Project> projects = user.getProjects();

            if (projects.size() == 0)
                return "";
            if (projects.size() == 1) {
                sb.append(" and basicInfo.projectName = '").append(projects.get(0).getProjectId()).append("\' ");
                return sb.toString();
            } else {
                StringBuilder tempRight = new StringBuilder();
                tempRight.append('(');
                for (Project project : projects) {
                    tempRight.append("'").append(project.getProjectId()).append("\'").append(",");
                }
                tempRight.setCharAt(tempRight.length() - 1, ')');

                sb.append(" and basicInfo.projectName in ").append(tempRight);

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
