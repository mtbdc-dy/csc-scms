package gov.gwssi.csc.scms.domain.scholarship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by gaochen 20150817.
 * 奖学金评审
 */
@Entity
@Table(name = "v_scholarship_unreport")
public class ScholarshipJ {
    @Id
    //@Column(length = 19)
    private Long id;
    /**
     * 年度
     */
    @Column
    private long year;
    /**
     * 奖学金评审状态
     */
    @Column
    private Character state;
    /**
     * 省市
     */
    @Column
    private String province;
    /**
     * 当前院校名称
     */
    @Column
    private String univ;
    /**
     * 学校评审提交时间
     */
    @Column
    private String schooldate;
    /**
     * 是否有不合格人数
     */
    @Column
    private String qualified;
    /**
     * 参评人数
     */
    @Column
    private Long count;
    /**
     * 合格人数
     */
    @Column
    private Long qualnum;
    /**
     * 奖学金评审状态
     */
    @Column
    private Long unqualnum;
    /**
     * 基金委批复时间
     */
    @Column
    private String cscdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }

    public String getSchooldate() {
        return schooldate;
    }

    public void setSchooldate(String schooldate) {
        this.schooldate = schooldate;
    }

    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getQualnum() {
        return qualnum;
    }

    public void setQualnum(Long qualnum) {
        this.qualnum = qualnum;
    }

    public Long getUnqualnum() {
        return unqualnum;
    }

    public void setUnqualnum(Long unqualnum) {
        this.unqualnum = unqualnum;
    }

    public String getCscdate() {
        return cscdate;
    }

    public void setCscdate(String cscdate) {
        this.cscdate = cscdate;
    }
}