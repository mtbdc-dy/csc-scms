package gov.gwssi.csc.scms.domain.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/8/11.
 */
public class NewStuTimeSetResult implements Serializable{
    private String univId;
    private String province;
    private String newSetBy;
    private Date newSeted;
    private Date newBegin;
    private Date newEnd;

    public String getUnivId() {
        return univId;
    }

    public void setUnivId(String univId) {
        this.univId = univId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNewSetBy() {
        return newSetBy;
    }

    public void setNewSetBy(String newSetBy) {
        this.newSetBy = newSetBy;
    }

    public Date getNewSeted() {
        return newSeted;
    }

    public void setNewSeted(Date newSeted) {
        this.newSeted = newSeted;
    }

    public Date getNewBegin() {
        return newBegin;
    }

    public void setNewBegin(Date newBegin) {
        this.newBegin = newBegin;
    }

    public Date getNewEnd() {
        return newEnd;
    }

    public void setNewEnd(Date newEnd) {
        this.newEnd = newEnd;
    }
}
