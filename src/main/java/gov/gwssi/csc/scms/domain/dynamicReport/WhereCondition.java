package gov.gwssi.csc.scms.domain.dynamicReport;

/**
 * Created by wangzishi on 15/9/28.
 */
public class WhereCondition {
    private String lParenthese;
    private String table;
    private String column;
    private String operator;
    private String condition;
    private String rParenthese;
    private String logic;

    public String getlParenthese() {
        return lParenthese;
    }

    public void setlParenthese(String lParenthese) {
        this.lParenthese = lParenthese;
    }

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getrParenthese() {
        return rParenthese;
    }

    public void setrParenthese(String rParenthese) {
        this.rParenthese = rParenthese;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }
}
