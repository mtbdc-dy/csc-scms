package gov.gwssi.csc.scms.domain.dictionary;

/**
 * Created by WangZhenghua on 2015/4/27.
 * 代码表转义(一层)
 */
public class TranslateDictJson {

    private String code;
    private String value;
    private boolean valid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
