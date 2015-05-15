package gov.gwssi.csc.scms.domain.statistics;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by WangZhenghua on 2015/5/13.
 * 动态查询统计条件配置
 */

@Entity
@Table(name ="SCMS_D_CONDITION")
public class DCondition {
    @Id
    private String id;
    // 配置编号
    private String configId;
    // 条件序号
    private String conditionNo;
    // 条件类型(1关联条件、2筛选条件)
    private String conditionType;
    // 关联类型(1等值、2左外、3右外、4全连接)
    private String joinType;
    // 左括号
    private String lParentheses;
    // 关联主表英文名称
    private String lTableEn;
    // 关联主表中文名称
    private String lTableCh;
    // 关联主表字段英文名称
    private String lColumnEn;
    // 关联主表字段中文名称
    private String lColumnCh;
    // 关联附表英文名称
    private String rTableEn;
    // 关联附表中文名称
    private String rTableCh;
    // 关联附表字段英文名称
    private String rColumnEn;
    // 关联附表字段中文名称
    private String rColumnCh;
    // 筛选表英文名称
    private String filterColumnEn;
    // 筛选表中文名称
    private String filterColumnCh;
    // 操作符
    private String operateSign;
    // 检索条件
    private String search;
    // 右括号
    private String rParentheses;
    // 逻辑运算符
    private String logicalOptSign;

    public DCondition() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConditionNo() {
        return conditionNo;
    }

    public void setConditionNo(String conditionNo) {
        this.conditionNo = conditionNo;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getlParentheses() {
        return lParentheses;
    }

    public void setlParentheses(String lParentheses) {
        this.lParentheses = lParentheses;
    }

    public String getlTableEn() {
        return lTableEn;
    }

    public void setlTableEn(String lTableEn) {
        this.lTableEn = lTableEn;
    }

    public String getlTableCh() {
        return lTableCh;
    }

    public void setlTableCh(String lTableCh) {
        this.lTableCh = lTableCh;
    }

    public String getlColumnEn() {
        return lColumnEn;
    }

    public void setlColumnEn(String lColumnEn) {
        this.lColumnEn = lColumnEn;
    }

    public String getlColumnCh() {
        return lColumnCh;
    }

    public void setlColumnCh(String lColumnCh) {
        this.lColumnCh = lColumnCh;
    }

    public String getrTableEn() {
        return rTableEn;
    }

    public void setrTableEn(String rTableEn) {
        this.rTableEn = rTableEn;
    }

    public String getrTableCh() {
        return rTableCh;
    }

    public void setrTableCh(String rTableCh) {
        this.rTableCh = rTableCh;
    }

    public String getrColumnEn() {
        return rColumnEn;
    }

    public void setrColumnEn(String rColumnEn) {
        this.rColumnEn = rColumnEn;
    }

    public String getrColumnCh() {
        return rColumnCh;
    }

    public void setrColumnCh(String rColumnCh) {
        this.rColumnCh = rColumnCh;
    }

    public String getFilterColumnEn() {
        return filterColumnEn;
    }

    public void setFilterColumnEn(String filterColumnEn) {
        this.filterColumnEn = filterColumnEn;
    }

    public String getFilterColumnCh() {
        return filterColumnCh;
    }

    public void setFilterColumnCh(String filterColumnCh) {
        this.filterColumnCh = filterColumnCh;
    }

    public String getOperateSign() {
        return operateSign;
    }

    public void setOperateSign(String operateSign) {
        this.operateSign = operateSign;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getrParentheses() {
        return rParentheses;
    }

    public void setrParentheses(String rParentheses) {
        this.rParentheses = rParentheses;
    }

    public String getLogicalOptSign() {
        return logicalOptSign;
    }

    public void setLogicalOptSign(String logicalOptSign) {
        this.logicalOptSign = logicalOptSign;
    }
}
