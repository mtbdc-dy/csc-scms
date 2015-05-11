package gov.gwssi.csc.scms.domain.scholarship;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String year;
    /**
     *院校 DIM_UNIVERSITY代码
     */
    @Column(name = "school",length=6)
    private String school;


    /**
     *创建日期
     */
    @Column(name = "time")
    private Date time;

    /**
     *合格人数
     */
    @Column(name = "qualNum",length=16)
    private Long qualNum;

    /**
     *不合格人数
     */
    @Column(name = "unQualNum",length=16)
    private Long unQualNum;
    /**
     *创建人
     */
    @Column(name = "CREATEBY",length=20)
    private String createBy;
    /**
     *入库日期
     */
    @Column(name = "CREATED")
    private String created;
    /**
     *修改人
     */
    @Column(name = "UPDATEBY",length=20)
    private String updateBy;
    /**
     *修改时间
     */
    @Column(name = "UPDATED")
    private String updated;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getQualNum() {
        return qualNum;
    }

    public void setQualNum(Long qualNum) {
        this.qualNum = qualNum;
    }

    public Long getUnQualNum() {
        return unQualNum;
    }

    public void setUnQualNum(Long unQualNum) {
        this.unQualNum = unQualNum;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
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
}
