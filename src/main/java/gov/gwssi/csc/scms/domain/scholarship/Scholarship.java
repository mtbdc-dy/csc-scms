package gov.gwssi.csc.scms.domain.scholarship;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/14.
 * 奖学金评审
 */
@Entity
@Table(name = "SCMS_SCHOLARSHIP")
public class Scholarship {
    @Id

    //@Column(length = 19)
    private String id;

    /**
     *年度
     */
    @Column(name = "year",length=4)
    private long year;
    /**
     *院校 DIM_UNIVERSITY代码
     */
    @Column(name = "school",length=6)
    private String school;

    /**
     *合格人数——学校用户
     */
    @Column(name = "schoolqual",length=16)
    private Long schoolQual;

    /**
     *不合格人数——学校用户
     */
    @Column(name = "SCHOOLUNQUAL",length=16)
    private Long schoolUnQual;
    /**
     *合格人数--基金委用户
     */
    @Column(name = "CSCQUAL",length=16)
    private Long cscQual;

    /**
     *不合格人数--基金委用户
     */
    @Column(name = "CSCUNQUAL",length=16)
    private Long cscUnQual;
    /**
     *创建人
     */
    @Column(name = "CREATEBY",length=20)
    private String createBy;
    /**
     *入库日期
     */
    @Column(name = "CREATED")
    private Date created;
    /**
     *修改人
     */
    @Column(name = "UPDATEBY",length=20)
    private String updateBy;
    /**
     *修改时间
     */
    @Column(name = "UPDATED")
    private Date updated;
    /**
     *基金委状态：0未提交；1提交
     */
    @Column(name = "CSCSTA",length=1)
    private String cscSta;
    /**
     *学校状态：0未提交；1提交
     */
    @Column(name = "SCHOOLSTA",length=1)
    private String schoolSta;
    /**
     *学校评审提交日期
     */
    @Column(name = "SCHOOLDATE")
    private Date schoolDate;

    /**
     *基金委批复日期
     */
    @Column(name = "CSCDATE")
    private Date cscDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Long getSchoolQual() {
        return schoolQual;
    }

    public void setSchoolQual(Long schoolQual) {
        this.schoolQual = schoolQual;
    }

    public Long getSchoolUnQual() {
        return schoolUnQual;
    }

    public void setSchoolUnQual(Long schoolUnQual) {
        this.schoolUnQual = schoolUnQual;
    }

    public Long getCscQual() {
        return cscQual;
    }

    public void setCscQual(Long cscQual) {
        this.cscQual = cscQual;
    }

    public Long getCscUnQual() {
        return cscUnQual;
    }

    public void setCscUnQual(Long cscUnQual) {
        this.cscUnQual = cscUnQual;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getCscSta() {
        return cscSta;
    }

    public void setCscSta(String cscSta) {
        this.cscSta = cscSta;
    }

    public String getSchoolSta() {
        return schoolSta;
    }

    public void setSchoolSta(String schoolSta) {
        this.schoolSta = schoolSta;
    }

    public Date getSchoolDate() {
        return schoolDate;
    }

    public void setSchoolDate(Date schoolDate) {
        this.schoolDate = schoolDate;
    }

    public Date getCscDate() {
        return cscDate;
    }

    public void setCscDate(Date cscDate) {
        this.cscDate = cscDate;
    }
}
