package gov.gwssi.csc.scms.domain.dynamicReport;

/**
 * Created by wangzishi on 15/9/28.
 */
public class JoinCondition {
    private String table;
    private String joinType;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }
}
