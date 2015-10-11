package gov.gwssi.csc.scms.domain.dynamicReport.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * 报表表头
 * Created by wangzishi on 15/10/10.
 */
public class ReportHead {
    private List<Cell> cells;

    public ReportHead(){
        this.cells = new ArrayList<Cell>();
    }

    public ReportHead(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
