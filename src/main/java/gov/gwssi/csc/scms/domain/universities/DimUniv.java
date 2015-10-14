package gov.gwssi.csc.scms.domain.universities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/8/31.
 */
@Entity
@Table(name = "DIM_UNIV")
public class DimUniv {

    @Id
    @Column(name = "univId")
    private String univId;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "NEWSETBY")
    private String newSetBy;

    @Column(name = "NEWBEGIN")
    private Date newBegin;
    @Column(name = "NEWEND")
    private Date newEnd;

    @Column(name = "NEWSETED")
    private Date newSeted;
    @Column(name = "OLDSETBY")
    private String oldSetBy;

    @Column(name = "OLDBEGIN")
    private Date oldBegin;
    @Column(name = "OLDEND")
    private Date oldEnd;

    @Column(name = "OLDSETED")
    private Date oldSeted;

    @Column(name = "ENABLED")
    private String enabled;

    public String getEnabled() {
        return enabled;
    }

    public String getUnivId() {
        return univId;
    }

    public String getProvince() {
        return province;
    }

    public String getNewSetBy() {
        return newSetBy;
    }

    public String getOldSetBy() {
        return oldSetBy;
    }

    public Date getNewBegin() {
        return newBegin;
    }

    public Date getNewEnd() {
        return newEnd;
    }

    public Date getNewSeted() {
        return newSeted;
    }

    public Date getOldBegin() {
        return oldBegin;
    }

    public Date getOldEnd() {
        return oldEnd;
    }

    public Date getOldSeted() {
        return oldSeted;
    }
}
