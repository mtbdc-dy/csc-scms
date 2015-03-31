package gov.gwssi.csc.scms.domain.other;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 *
 */
@Entity
@Table(name = "SCMS_ADDRESS_TYPE")
public class AddressType {
    @Id
    private Long id;
    private String address_type_name;
    private String address_type_no;
    private String update_by;
    private String enabled;
    private java.util.Date update_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress_type_name() {
        return address_type_name;
    }

    public void setAddress_type_name(String address_type_name) {
        this.address_type_name = address_type_name;
    }

    public String getAddress_type_no() {
        return address_type_no;
    }

    public void setAddress_type_no(String address_type_no) {
        this.address_type_no = address_type_no;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public java.util.Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(java.util.Date update_date) {
        this.update_date = update_date;
    }
}
