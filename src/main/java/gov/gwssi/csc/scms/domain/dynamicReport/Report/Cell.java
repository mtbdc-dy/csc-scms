package gov.gwssi.csc.scms.domain.dynamicReport.Report;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;

import javax.persistence.*;

/**
 * 报表单元格
 * Created by wangzishi on 15/10/10.
 */
@Entity
@Table(name = "SCMS_D_CELL")
public class Cell {
    private String id;
    private Integer rowSpan;
    private Integer colSpan;
    private String value;
    private Configuration config;
    public Cell() {}

    public Cell(String id) {
        this.id = id;
        this.rowSpan = 1;
        this.colSpan = 1;
    }
    public Cell(String id, Integer rowSpan, Integer colSpan, String value) {
        this.id = id;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
        this.value = value;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "ROW_SPAN")
    public Integer getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
    }

    @Column(name = "COL_SPAN")
    public Integer getColSpan() {
        return colSpan;
    }

    public void setColSpan(Integer colSpan) {
        this.colSpan = colSpan;
    }

    @Column(columnDefinition = "CLOB")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "CFG_ID")
    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
