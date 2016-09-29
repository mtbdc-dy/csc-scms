package gov.gwssi.csc.scms.domain.dynamicReport.ASMSReport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.gwssi.csc.scms.domain.dynamicReport.ASMSConfig;
import gov.gwssi.csc.scms.domain.dynamicReport.ASMSConfiguration.Configuration;

import javax.persistence.*;

/**
 * 报表单元格
 * Created by wangzishi on 15/10/10.
 */
@Entity
@Table(name = "ASMS_D_CFG_CELL")
public class Cell extends ASMSConfig implements Comparable<Cell>{
    private String id;
    private Integer rowNumber;
    private Integer rowSpan;
    private Integer colSpan;
    private String value;
    private Configuration config;
    public Cell() {}

    public Cell(String id) {
        this.id = id;
        this.rowNumber = 1;
        this.rowSpan = 1;
        this.colSpan = 1;
    }

    public Cell(String id, Integer rowSpan, Integer colSpan, String value) {
        this.id = id;
        this.rowNumber = 1;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
        this.value = value;
    }

    public Cell(String id, Integer rowNumber, Integer rowSpan, Integer colSpan, String value) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
        this.value = value;
    }

    public Cell(Integer rowSpan, Integer colSpan, String value){
        this.rowSpan = rowSpan;
        this.colSpan = colSpan;
        this.value = value;
    }

    @Id
    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    @Column(name = "ROW_NUMBER")
    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
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
    @JsonIgnore
    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }


    @Override
    public int compareTo(Cell cell) {
        Long thisCellId = new Long(this.getId());
        Long thatCellId = new Long(cell.getId());
        return thisCellId.compareTo(thatCellId);
    }
}
