package gov.gwssi.csc.scms.domain.dynamicReport;

import javax.persistence.*;

/**
 * Created by wangzishi on 15/9/2.
 * 字段代码表的域对象类
 */
@Entity
@javax.persistence.Table(name = "dim_column")
public class Column{

    private String id;
    private String columnEn;
    private String columnCh;
    private String DataType;
    private String keyType;
    private String referenceTableEn;
    private String referenceTableCh;
    private String referenceColumnEn;
    private String referenceColumnCh;
    private Table table;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tableId")
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
