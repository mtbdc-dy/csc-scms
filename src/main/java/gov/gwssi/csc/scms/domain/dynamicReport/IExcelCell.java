package gov.gwssi.csc.scms.domain.dynamicReport;

/**
 *
 * Created by Anaaaaaa on 2016/9/29.
 */
public interface IExcelCell {
    public String getId();

    public void setId(String id);

    public Integer getFirstColumn();

    public void setFirstColumn(Integer firstColumn);

    public Integer getFirstRow();

    public void setFirstRow(Integer firstRow);

    public Integer getLastColumn();

    public void setLastColumn(Integer lastColumn);

    public Integer getLastRow();

    public void setLastRow(Integer lastRow);

    public String getColumnHeader();

    public void setColumnHeader(String columnHeader);

    public String getValue();

    public void setValue(String value);
}
