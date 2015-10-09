package gov.gwssi.csc.scms.domain.dynamicReport;

import java.util.List;
import java.util.Set;

/**
 * Created by wangzishi on 15/9/28.
 */
public class OriginalConfiguration {
    private String title;
    private String description;
    private String accessState;
    private Set<JoinCondition> joinConditions;
    private List<WhereCondition> whereConditions;
    private Set<GroupCondition> groupConditions;
    private Set<OrderCondition> orderConditions;
    private List<SelectCondition> selectConditions;
    private String rawConfig;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessState() {
        return accessState;
    }

    public void setAccessState(String accessState) {
        this.accessState = accessState;
    }

    public Set<JoinCondition> getJoinConditions() {
        return joinConditions;
    }

    public void setJoinConditions(Set<JoinCondition> joinConditions) {
        this.joinConditions = joinConditions;
    }

    public List<WhereCondition> getWhereConditions() {
        return whereConditions;
    }

    public void setWhereConditions(List<WhereCondition> whereConditions) {
        this.whereConditions = whereConditions;
    }

    public Set<GroupCondition> getGroupConditions() {
        return groupConditions;
    }

    public void setGroupConditions(Set<GroupCondition> groupConditions) {
        this.groupConditions = groupConditions;
    }

    public Set<OrderCondition> getOrderConditions() {
        return orderConditions;
    }

    public void setOrderConditions(Set<OrderCondition> orderConditions) {
        this.orderConditions = orderConditions;
    }

    public List<SelectCondition> getSelectConditions() {
        return selectConditions;
    }

    public void setSelectConditions(List<SelectCondition> selectConditions) {
        this.selectConditions = selectConditions;
    }

    public String getRawConfig() {
        return rawConfig;
    }

    public void setRawConfig(String rawConfig) {
        this.rawConfig = rawConfig;
    }
}