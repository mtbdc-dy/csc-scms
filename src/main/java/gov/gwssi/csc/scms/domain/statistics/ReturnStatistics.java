package gov.gwssi.csc.scms.domain.statistics;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/6/12.
 */
public class ReturnStatistics {

    private List<Header> headers;
    private List resultList;

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }
}
