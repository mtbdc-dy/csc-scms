package gov.gwssi.csc.scms.domain.dynamicReport.Configuration;

import gov.gwssi.csc.scms.domain.dynamicReport.Config;

/**
 * Created by wangzishi on 15/10/13.
 */
public abstract class Condition extends Config {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
