package gov.gwssi.csc.scms.domain.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;

/**
 * Created by wangzishi on 15/10/13.
 */
public abstract class Config {
    private Configuration config;

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
