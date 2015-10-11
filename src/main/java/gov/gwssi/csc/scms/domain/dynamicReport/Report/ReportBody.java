package gov.gwssi.csc.scms.domain.dynamicReport.Report;

import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;

/**
 * 报表内容
 * Created by wangzishi on 15/10/10.
 */
public class ReportBody {
    private List<Cell> cells;

    public ReportBody(){
        this.cells = new ArrayList<Cell>();
    }

    public ReportBody(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
