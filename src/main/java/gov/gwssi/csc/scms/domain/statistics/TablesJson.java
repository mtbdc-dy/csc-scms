package gov.gwssi.csc.scms.domain.statistics;


import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/29.
 */
public class TablesJson {
    private String code;
    private String codePid;
    private boolean valid;
    private ValueObject value;
    private List<TablesJson> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodePid() {
        return codePid;
    }

    public void setCodePid(String codePid) {
        this.codePid = codePid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public ValueObject getValue() {
        return value;
    }

    public void setValue(ValueObject value) {
        this.value = value;
    }

    public List<TablesJson> getChildren() {
        return children;
    }

    public void setChildren(List<TablesJson> children) {
        this.children = children;
    }
}
