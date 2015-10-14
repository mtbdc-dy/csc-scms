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
    private String dataType;
    private String inputType;
    private String codeTable;
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
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getCodeTable() {
        return codeTable;
    }

    public void setCodeTable(String codeTable) {
        this.codeTable = codeTable;
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
