package gov.gwssi.csc.scms.domain.dynamicReport.Report;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;

import javax.persistence.*;

/**
 * 坐标定位单元格
 * Created by wangzishi on 15/10/28.
 */
@Entity
@Table(name = "SCMS_D_HEADERMERGE")
public class ExcelCell {
    private String id;
    private Integer firstRow;
    private Integer firstColumn;
    private Integer lastRow;
    private Integer lastColumn;
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

    @Column(name = "YCOORDINATE1")
    public Integer getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(Integer firstColumn) {
        this.firstColumn = firstColumn;
    }

    @Column(name = "XCOORDINATE1")
    public Integer getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(Integer firstRow) {
        this.firstRow = firstRow;
    }

    @Column(name = "YCOORDINATE2")
    public Integer getLastColumn() {
        return lastColumn;
    }

    public void setLastColumn(Integer lastColumn) {
        this.lastColumn = lastColumn;
    }

    @Column(name = "XCOORDINATE2")
    public Integer getLastRow() {
        return lastRow;
    }

    public void setLastRow(Integer lastRow) {
        this.lastRow = lastRow;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
