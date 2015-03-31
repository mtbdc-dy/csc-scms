package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**相关地址
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "LHLX_RELATED_ADDRESS")
public class RelatedAddress {
    @Id private Long id;
    private java.sql.Date update_date;
    private String type;
    private String fax;
    private String name;
    private String nature;
    private String update_by;
    private String address;
    private String email;
    private String remark;
    private String phone;

    @OneToOne
    private Student student;

    public java.sql.Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(java.sql.Date update_date) {
        this.update_date = update_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
