package gov.gwssi.csc.scms.domain.regstatistics;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Wang Rui on 2015/6/22.
 */
@Entity
@Table(name = "SCMS_STATS_REGISTER")
public class RegStatistics {
    @Id
    private String id;
    private String no;
    private String province;
    private String university;
    private Integer totalNum;
    private Integer registeredNum;
    private Integer giveUpNum;
    private Integer unhandledNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getRegisteredNum() {
        return registeredNum;
    }

    public void setRegisteredNum(Integer registeredNum) {
        this.registeredNum = registeredNum;
    }

    public Integer getGiveUpNum() {
        return giveUpNum;
    }

    public void setGiveUpNum(Integer giveUpNum) {
        this.giveUpNum = giveUpNum;
    }

    public Integer getUnhandledNum() {
        return unhandledNum;
    }

    public void setUnhandledNum(Integer unhandledNum) {
        this.unhandledNum = unhandledNum;
    }
}
