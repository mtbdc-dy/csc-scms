package gov.gwssi.csc.scms.controller;

/**
 * Created by Murray on 2015/4/28.
 */
public class JsonBody {
    private String value;
    private String log;
    private String user;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
