package gov.gwssi.csc.scms.domain.student;

import gov.gwssi.csc.scms.utils.UnicodeUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * 相关地址
 */
@Entity
@Table(name = "SCMS_RELATED_ADDRESS")
public class RelatedAddress {
    @Id
    private String id;
    /**
     * 类别
     */
    private String type;
    /**
     * 性质
     */
    private String nature;
    /**
     * 详细地址或紧急联系人
     */
    private String addressOrName;
    /**
     * 电话
     */
    private String phone;
    /**
     * 传真
     */
    private String fax;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getAddressOrName() {
        return UnicodeUtil.toCharSequence(addressOrName);
    }

    public void setAddressOrName(String addressOrName) {
        this.addressOrName = UnicodeUtil.toUNICODE(addressOrName);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return UnicodeUtil.toCharSequence(remark);
    }

    public void setRemark(String remark) {
        this.remark = UnicodeUtil.toUNICODE(remark);
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
}
