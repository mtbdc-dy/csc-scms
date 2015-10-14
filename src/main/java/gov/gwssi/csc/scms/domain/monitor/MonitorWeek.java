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
@Table(name = "v_monitor_week")
public class MonitorWeek {
    @Id
    private Date week;
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

    public Date getWeek() {
        return week;
    }

    public void setWeek(Date week) {
        this.week = week;
    }
}
