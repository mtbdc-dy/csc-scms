package gov.gwssi.csc.scms.domain.dynamicReport;

import java.io.Serializable;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 所有表对应的所有字段
 */

public class DimColumn implements Serializable{
    // 表（英）
    private String tableEn;
    // 表（中）
    private String tableCh;
    // 字段（英）
    private String columnEn;
    // 字段（中）
    private String columnCh;
    // 数据类型
    private String dataType;
    // 键类型；1主键；2联合主键；3外键；4无
    private String keyType;
    // 引用表（英）
    private String referenceTableEn;
    // 引用表（中）
    private String referenceTableCh;
    // 引用字段（英）
    private String referenceColumnEn;
    // 引用字段（中）
    private String referenceColumnCh;

    public DimColumn() {
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

    public void setColumnCn(String columnCn) {
        this.columnCh = columnCn;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public void setColumnCh(String columnCh) {
        this.columnCh = columnCh;
    }

    public String getReferenceTableEn() {
        return referenceTableEn;
    }

    public void setReferenceTableEn(String referenceTableEn) {
        this.referenceTableEn = referenceTableEn;
    }

    public String getReferenceTableCh() {
        return referenceTableCh;
    }

    public void setReferenceTableCh(String referenceTableCh) {
        this.referenceTableCh = referenceTableCh;
    }

    public String getReferenceColumnEn() {
        return referenceColumnEn;
    }

    public void setReferenceColumnEn(String referenceColumnEn) {
        this.referenceColumnEn = referenceColumnEn;
    }

    public String getReferenceColumnCh() {
        return referenceColumnCh;
    }

    public void setReferenceColumnCh(String referenceColumnCh) {
        this.referenceColumnCh = referenceColumnCh;
    }

}
