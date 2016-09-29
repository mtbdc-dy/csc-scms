package gov.gwssi.csc.scms.domain.dynamicReport.ASMSReport;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态报表行
 * Created by wangzishi on 15/10/21.
 */
public class  Row {
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
