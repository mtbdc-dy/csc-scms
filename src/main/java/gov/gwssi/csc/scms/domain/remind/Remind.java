package gov.gwssi.csc.scms.domain.remind;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianj on 2015/8/12.
 */
@Entity
@Table(name = "V_REMIND")
public class Remind {
    @Id

    private int seq;

    @Column
    private String remind;

    @Column
    private int cnt;

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
