package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 相关地址
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_RELATED_ADDRESS")
public class RelatedAddress {
    @Id
    private Long id;
    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "student")//加入一列作为外键
    private Student student;
    private String type;//类别
    private String nature;//性质
    private String address_or_name;//详细地址/紧急联系人姓名
    private String phone;//电话
    private String fax;//传真
    private String email;//邮箱
    private String remark;//备注

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

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

    public String getAddress_or_name() {
        return address_or_name;
    }

    public void setAddress_or_name(String address_or_name) {
        this.address_or_name = address_or_name;
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
}
