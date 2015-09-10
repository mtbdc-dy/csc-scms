package gov.gwssi.csc.scms.controller.dynamicReport;

/**
 * Created by WangZhenghua on 2015/6/11.
 */

public class JsonBody {
    private String DTableList;
    private String DConfig;
    private String DConditions;
    private String DDisplays;

    public String getDTableList() {
        return DTableList;
    }

    public void setDTableList(String DTableList) {
        this.DTableList = DTableList;
    }

    public String getDConfig() {
        return DConfig;
    }

    public void setDConfig(String DConfig) {
        this.DConfig = DConfig;
    }

    public String getDConditions() {
        return DConditions;
    }

    public void setDConditions(String DConditions) {
        this.DConditions = DConditions;
    }

    public String getDDisplays() {
        return DDisplays;
    }

    public void setDDisplays(String DDisplays) {
        this.DDisplays = DDisplays;
    }
}
