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
@Table(name = "v_monitor_month")
public class MonitorMonth {
    @Id
    private Date month;
    @Column
    private Integer avg;
    @Column
    private Integer max;
    @Column
    private Integer min;

    public Integer getAvg() {
        return avg;
    }

    public void setAvg(Integer avg) {
        this.avg = avg;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }
}
