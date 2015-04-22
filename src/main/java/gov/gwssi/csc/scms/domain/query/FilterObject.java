package gov.gwssi.csc.scms.domain.query;

import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public abstract class FilterObject {

    private String offSet = "0";

    private String pageSize = "200";

    public String getOffSet() {
        return offSet;
    }

    public void setOffSet(String offSet) {
        this.offSet = offSet;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    abstract List getConditions();

}
