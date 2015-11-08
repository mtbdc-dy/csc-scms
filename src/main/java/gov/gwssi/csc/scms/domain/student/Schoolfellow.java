package gov.gwssi.csc.scms.domain.student;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * 校友信息
 */

@Entity
@Table(name = "SCMS_SCHOOL_FELLOW")
public class Schoolfellow implements Cloneable{
    @Id
    private String id;
    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 职位
     */
    private String position;
    /**
     * 办公电话
     */
    private String officePhone;
    /**
     * 手机
     */
    private String telephone;
    /**
     * 电子邮箱地址
     */
    private String email;
    /**
     * 单位地址
     */
    private String officeAddress;
    /**
     * 回国情况
     */
    private String backSituation;
    /**
     * 成就信息
     */
    private String achievementInfo;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @Column(name = "CREATED")
    private Date createDate;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    @Column(name = "UPDATED")
    private Date updateDate;
    /**
     * 学生id
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getBackSituation() {
        return backSituation;
    }

    public void setBackSituation(String backSituation) {
        this.backSituation = backSituation;
    }

    public String getAchievementInfo() {
        return achievementInfo;
    }

    public void setAchievementInfo(String achievementInfo) {
        this.achievementInfo = achievementInfo;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public Schoolfellow clone() throws CloneNotSupportedException{
        return (Schoolfellow)super.clone();
    }
}
