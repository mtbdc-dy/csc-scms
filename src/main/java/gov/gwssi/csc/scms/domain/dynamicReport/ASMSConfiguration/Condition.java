package gov.gwssi.csc.scms.domain.dynamicReport.ASMSConfiguration;

import gov.gwssi.csc.scms.domain.dynamicReport.ASMSConfig;

/**
 *
 * Created by wangzishi on 15/10/13.
 */
public abstract class Condition extends ASMSConfig {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
