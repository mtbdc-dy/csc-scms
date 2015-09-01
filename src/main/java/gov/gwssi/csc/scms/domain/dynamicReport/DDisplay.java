package gov.gwssi.csc.scms.domain.dynamicReport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by WangZhenghua on 2015/5/13.
 * 显示样式
 */

@Entity
@Table(name ="SCMS_D_DISPLAY")
public class DDisplay implements Serializable{
    @Id
    private String id;
    // 配置id
    private String configId;
    // 显示序号
    private String displayNo;
    // 表名（英）
    private String tableEn;
    // 表名（中）
    private String tableCh;
    // 字段名（英）
    private String columnEn;
    // 字段名（中）
    private String columnCh;
    // 数据类型
    private String dataType;
    // 对齐方式：1居中；2靠左；3靠右
    private String alignment;
    // 字段宽度
    private long columnWidth;
    // 排序：0无；1升序；2降序
    private String sort;
    // 计算方式：1分组字段；2简单计数；3代码计数；4简单累计；5代码累计
    private String aggregate;
    // 相关表
    private String relateTable;
    // 相关字段(英)
    private String relateColumnEn;
    // 相关字段(中)
    private String relateColumnCh;
    // 层级关系
    private String hierarchy;
    // 显示名称
    private String displayName;
    // 是否页面显示：0否；1是
    private String isDisplay;
    // 是否需要小计：0否；1是
    private String isSubTotal;
    // 是否需要合计：0否；1是
    private String isTotal;

    public DDisplay() {
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

    public String getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(String displayNo) {
        this.displayNo = displayNo;
    }

    public String getTableEn() {
        return tableEn;
    }

    public void setTableEn(String tableEn) {
        this.tableEn = tableEn;
    }

    public String getTableCh() {
        return tableCh;
    }

    public void setTableCh(String tableCh) {
        this.tableCh = tableCh;
    }

    public String getColumnEn() {
        return columnEn;
    }

    public void setColumnEn(String columnEn) {
        this.columnEn = columnEn;
    }

    public String getColumnCh() {
        return columnCh;
    }

    public void setColumnCh(String columnCh) {
        this.columnCh = columnCh;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public long getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(long columnWidth) {
        this.columnWidth = columnWidth;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAggregate() {
        return aggregate;
    }

    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }

    public String getRelateTable() {
        return relateTable;
    }

    public void setRelateTable(String relateTable) {
        this.relateTable = relateTable;
    }

    public String getRelateColumnEn() {
        return relateColumnEn;
    }

    public void setRelateColumnEn(String relateColumnEn) {
        this.relateColumnEn = relateColumnEn;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getIsSubTotal() {
        return isSubTotal;
    }

    public void setIsSubTotal(String isSubTotal) {
        this.isSubTotal = isSubTotal;
    }

    public String getIsTotal() {
        return isTotal;
    }

    public void setIsTotal(String isTotal) {
        this.isTotal = isTotal;
    }

    public String getRelateColumnCh() {
        return relateColumnCh;
    }

    public void setRelateColumnCh(String relateColumnCh) {
        this.relateColumnCh = relateColumnCh;
    }
}
