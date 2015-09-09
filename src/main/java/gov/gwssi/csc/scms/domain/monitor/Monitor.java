package gov.gwssi.csc.scms.domain.monitor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by tianj on 2015/9/7.
 */
@Entity
@Table(name = "SCMS_MONITOR")
public class Monitor {
    @Id
    private String id;
    @Column
    private Integer userCount;
    @Column
    private Date createD;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Date getCreateD() {
        return createD;
    }

    public void setCreateD(Date createD) {
        this.createD = createD;
    }
}
