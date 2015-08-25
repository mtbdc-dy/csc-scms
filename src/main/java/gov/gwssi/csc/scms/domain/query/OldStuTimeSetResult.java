package gov.gwssi.csc.scms.domain.query;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/8/11.
 */
public class OldStuTimeSetResult implements Serializable{
    private String univId;
    private String province;
    private String oldSetBy;
    private Date oldSeted;
    private Date oldBegin;
    private Date oldEnd;

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

    public String getOldSetBy() {
        return oldSetBy;
    }

    public void setOldSetBy(String oldSetBy) {
        this.oldSetBy = oldSetBy;
    }

    public Date getOldSeted() {
        return oldSeted;
    }

    public void setOldSeted(Date oldSeted) {
        this.oldSeted = oldSeted;
    }

    public Date getOldBegin() {
        return oldBegin;
    }

    public void setOldBegin(Date oldBegin) {
        this.oldBegin = oldBegin;
    }

    public Date getOldEnd() {
        return oldEnd;
    }

    public void setOldEnd(Date oldEnd) {
        this.oldEnd = oldEnd;
    }
}
