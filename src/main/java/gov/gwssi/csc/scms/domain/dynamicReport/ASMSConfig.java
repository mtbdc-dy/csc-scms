package gov.gwssi.csc.scms.domain.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.ASMSConfiguration.Configuration;

/**
 *
 * Created by fangdi on 16/9/27.
 */
public abstract class ASMSConfig {
    private Configuration config;

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
