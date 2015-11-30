package gov.gwssi.csc.scms.domain.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lzs on 2015/7/17.
 * //迫不得已 全用大写了
 */
public class CodeDetailResult implements Serializable
{
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
     * 招生代码
     */
    private String CODE;
    /**
     * 主管部门
     */
    private String ADMINDEPT;
    /**
     * 归属
     */
    private String TYPE;
    /**
     * 修改时间
     */
    private Date   UPDATED;
    /**
     * 父节点
     */
    private String PARENTID;

    /**
     * 经费属性
     */
    private String FUNDATTR;

    /**
     * 经费标准
     */
    private String FUNDSTANDARD;


    public CodeDetailResult()
    {
    }

    public CodeDetailResult(String ID, String NAME, String ENABLED, String TABLEEN, String FULLNAME, Date UPDATED)
    {
        this.ID = ID;
        this.NAME = NAME;
        this.ENABLED = ENABLED;
        this.TABLEEN = TABLEEN;
        this.FULLNAME = FULLNAME;
        this.UPDATED = UPDATED;
    }

    public CodeDetailResult(String ID, String ENABLED, String NAME, String FULLNAME, String TABLEEN, Date UPDATED, String PARENTID)
    {
        this.ID = ID;
        this.NAME = NAME;
        this.ENABLED = ENABLED;
        this.TABLEEN = TABLEEN;
        this.FULLNAME = FULLNAME;
        this.UPDATED = UPDATED;
        this.PARENTID = PARENTID;
    }

    public CodeDetailResult(String ID, String NAME, String ENABLED, String TABLEEN, String FULLNAME, String CODE, String ADMINDEPT, String TYPE, Date UPDATED, String PARENTID)
    {
        this.ID = ID;
        this.NAME = NAME;
        this.ENABLED = ENABLED;
        this.TABLEEN = TABLEEN;
        this.FULLNAME = FULLNAME;
        this.CODE = CODE;
        this.ADMINDEPT = ADMINDEPT;
        this.TYPE = TYPE;
        this.UPDATED = UPDATED;
        this.PARENTID = PARENTID;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    public String getENABLED()
    {
        return ENABLED;
    }

    public void setENABLED(String ENABLED)
    {
        this.ENABLED = ENABLED;
    }

    public String getTABLEEN()
    {
        return TABLEEN;
    }

    public void setTABLEEN(String TABLEEN)
    {
        this.TABLEEN = TABLEEN;
    }

    public String getFULLNAME()
    {
        return FULLNAME;
    }

    public void setFULLNAME(String FULLNAME)
    {
        this.FULLNAME = FULLNAME;
    }

    public Date getUPDATED()
    {
        return UPDATED;
    }

    public void setUPDATED(Date UPDATED)
    {
        this.UPDATED = UPDATED;
    }

    public String getPARENTID()
    {
        return PARENTID;
    }

    public void setPARENTID(String PARENTID)
    {
        this.PARENTID = PARENTID;
    }

    public String getCODE()
    {
        return CODE;
    }

    public void setCODE(String CODE)
    {
        this.CODE = CODE;
    }

    public String getADMINDEPT()
    {
        return ADMINDEPT;
    }

    public void setADMINDEPT(String ADMINDEPT)
    {
        this.ADMINDEPT = ADMINDEPT;
    }

    public String getTYPE()
    {
        return TYPE;
    }

    public void setTYPE(String TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getFUNDATTR()
    {
        return FUNDATTR;
    }

    public void setFUNDATTR(String FUNDATTR)
    {
        this.FUNDATTR = FUNDATTR;
    }

    public String getFUNDSTANDARD()
    {
        return FUNDSTANDARD;
    }

    public void setFUNDSTANDARD(String FUNDSTANDARD)
    {
        this.FUNDSTANDARD = FUNDSTANDARD;
    }
}
