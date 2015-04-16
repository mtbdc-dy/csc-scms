package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 相关地址
 */
@Entity
@Table(name = "SCMS_RELATED_ADDRESS")
public class RelatedAddress {
    @Id
    @SequenceGenerator(name = "RELATED_ADDRESS_ID", sequenceName = "SCMS_RRELATED_ADDRESS_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "RELATED_ADDRESS_ID", strategy = GenerationType.SEQUENCE)
    private Long id;
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
     * 学生id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return addressOrName;
    }

    public void setAddressOrName(String addressOrName) {
        this.addressOrName = addressOrName;
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
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
