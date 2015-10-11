package gov.gwssi.csc.scms.domain.dynamicReport.Report;

import java.util.ArrayList;
import java.util.List;

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

    public Report(List<Cell> headerCells, List<Cell> bodyCells){
        this.header = new ArrayList<Row>();
        
//        Row row = new Row()
//        for (Cell cell : headerCells) {
//
//        }
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

class Row {
    private List<Cell> cells;

    public Row(){
        this.cells = new ArrayList<Cell>();
    }

    public Row(List<Cell> cells){
        this.cells = cells;
    }

    public Boolean add(Cell cell){
        return this.cells.add(cell);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
