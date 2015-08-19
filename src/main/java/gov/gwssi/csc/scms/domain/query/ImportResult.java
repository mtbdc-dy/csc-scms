package gov.gwssi.csc.scms.domain.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/8/11.
 */
public class ImportResult implements Serializable{
    private String fileName;
    private String sumNo;
    private String importState;
    private Date optTime;
    private String opter;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    public String getImportState() {
        return importState;
    }

    public void setImportState(String importState) {
        this.importState = importState;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getSumNo() {
        return sumNo;
    }

    public void setSumNo(String sumNo) {
        this.sumNo = sumNo;
    }

    public String getOpter() {
        return opter;
    }

    public void setOpter(String opter) {
        this.opter = opter;
    }
}
