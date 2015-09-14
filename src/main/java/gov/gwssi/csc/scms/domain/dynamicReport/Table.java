package gov.gwssi.csc.scms.domain.dynamicReport;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by wangzishi on 15/9/2.
 * 各个表域对象
 */
@Entity
@javax.persistence.Table(name = "Dim_Table")
public class Table {
    private String id;
    private String tableEn;
    private String tableCh;
    private String tableType;
    private List<Column> columns;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY)
    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
