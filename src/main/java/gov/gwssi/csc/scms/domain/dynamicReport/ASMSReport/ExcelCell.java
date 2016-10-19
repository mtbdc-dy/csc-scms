package gov.gwssi.csc.scms.domain.dynamicReport.ASMSReport;

import gov.gwssi.csc.scms.domain.dynamicReport.ASMSConfiguration.Configuration;
import gov.gwssi.csc.scms.domain.dynamicReport.IExcelCell;

import javax.persistence.*;

/**
 * 坐标定位单元格
 * Created by wangzishi on 15/10/28.
 */
@Entity(name = "ASMSExcelCell")
@Table(name = "V_D_HEADERMERGE_ASMS", schema = "ASMS")
public class ExcelCell implements IExcelCell {
    private String id;
    private Integer firstRow;
    private Integer firstColumn;
    private Integer lastRow;
    private Integer lastColumn;
    private String columnHeader;
    private String value;
    private Configuration config;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "CONFIGID")
    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    @Column(name = "YCOORDINATE1", columnDefinition = "VARCHAR2(3)")
    public Integer getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(Integer firstColumn) {
        this.firstColumn = firstColumn;
    }

    @Column(name = "XCOORDINATE1", columnDefinition = "VARCHAR2(3)")
    public Integer getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(Integer firstRow) {
        this.firstRow = firstRow;
    }

    @Column(name = "YCOORDINATE2", columnDefinition = "VARCHAR2(3)")
    public Integer getLastColumn() {
        return lastColumn;
    }

    public void setLastColumn(Integer lastColumn) {
        this.lastColumn = lastColumn;
    }

    @Column(name = "XCOORDINATE2", columnDefinition = "VARCHAR2(3)")
    public Integer getLastRow() {
        return lastRow;
    }

    public void setLastRow(Integer lastRow) {
        this.lastRow = lastRow;
    }

    @Column(name = "COLUMN_HEADER", columnDefinition = "CHAR(1)")
    public String getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(String columnHeader) {
        this.columnHeader = columnHeader;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
