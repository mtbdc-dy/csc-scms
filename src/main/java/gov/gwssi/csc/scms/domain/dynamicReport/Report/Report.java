package gov.gwssi.csc.scms.domain.dynamicReport.Report;

import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 报表数据
 * Created by wangzishi on 15/10/10.
 */
public class Report {
    private List<Row> header;
    private List<Row> body;

    public Report(){
        this.header = new ArrayList<Row>();
        this.header.add(new Row());
    }

    public Report(List<Cell> headerCells, List<Map> bodyMaps){
        this.header = new ArrayList<Row>();
        Row row = new Row();
        Integer level = 1;
        for (Cell cell : headerCells) {
            if (cell.getRowNumber().equals(level)) {
                row.add(cell);
            } else {
                this.header.add(row);
                row = new Row();
                level = cell.getRowNumber();
                row.add(cell);
            }
        }
        this.header.add(row);

        this.body = new ArrayList<Row>();
        for (Map rowMap : bodyMaps) {
            row = new Row();
            for (Integer i = 0; i < rowMap.size(); i++) {
                Object obj = rowMap.get("COL" + i.toString());
                if (obj != null) {
                    row.add(new Cell(1,1, obj.toString()));
                } else {
                    row.add(new Cell(1,1, ""));
                }

            }
            this.body.add(row);
        }

    }

    public List<Row> getHeader() {
        return header;
    }

    public void setHeader(List<Row> header) {
        this.header = header;
    }

    public List<Row> getBody() {
        return body;
    }

    public void setBody(List<Row> body) {
        this.body = body;
    }
}
