package gov.gwssi.csc.scms.domain.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lzs on 2015/7/17.
 * //
 */
public class CodeDetailResult implements Serializable {
    /**
     * id
     */
    private String ID;
    /**
     * 中文名称
     */
    private String NAME;
    /**
     * 有效 标志
     */
    private String ENABLED;
    /**
     * 表名
     */
    private String TABLEEN;
    /**
     * 修改人
     */
    private String FULLNAME;

    /**
     * 秀改时间
     */
    private Date UPDATED;

    public CodeDetailResult() {
    }

    public CodeDetailResult(String ID, String ENABLED,String NAME,  String FULLNAME, String TABLEEN, Date UPDATED) {
        this.ID = ID;
        this.NAME = NAME;
        this.ENABLED = ENABLED;
        this.TABLEEN = TABLEEN;
        this.FULLNAME = FULLNAME;
        this.UPDATED = UPDATED;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getENABLED() {
        return ENABLED;
    }

    public void setENABLED(String ENABLED) {
        this.ENABLED = ENABLED;
    }

    public String getTABLEEN() {
        return TABLEEN;
    }

    public void setTABLEEN(String TABLEEN) {
        this.TABLEEN = TABLEEN;
    }

    public String getFULLNAME() {
        return FULLNAME;
    }

    public void setFULLNAME(String FULLNAME) {
        this.FULLNAME = FULLNAME;
    }

    public Date getUPDATED() {
        return UPDATED;
    }

    public void setUPDATED(Date UPDATED) {
        this.UPDATED = UPDATED;
    }
}
