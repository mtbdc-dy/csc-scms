package gov.gwssi.csc.scms.domain.other;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/3/31.
 */
@Entity
@Table(name = "SCMS_BLACKLIST")
public class Blacklist {
    @Id
    private Long id;
    private String add_username;
    private String remove_userid;
    private String add_reason;
    private String remove_username;
    private String csc_id;
    private String remove_reason;
    private Date add_time;
    private String state;
    private String add_userid;
    private java.sql.Date remove_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdd_username() {
        return add_username;
    }

    public void setAdd_username(String add_username) {
        this.add_username = add_username;
    }

    public String getRemove_userid() {
        return remove_userid;
    }

    public void setRemove_userid(String remove_userid) {
        this.remove_userid = remove_userid;
    }

    public String getAdd_reason() {
        return add_reason;
    }

    public void setAdd_reason(String add_reason) {
        this.add_reason = add_reason;
    }

    public String getRemove_username() {
        return remove_username;
    }

    public void setRemove_username(String remove_username) {
        this.remove_username = remove_username;
    }

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public String getRemove_reason() {
        return remove_reason;
    }

    public void setRemove_reason(String remove_reason) {
        this.remove_reason = remove_reason;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAdd_userid() {
        return add_userid;
    }

    public void setAdd_userid(String add_userid) {
        this.add_userid = add_userid;
    }

    public java.sql.Date getRemove_time() {
        return remove_time;
    }

    public void setRemove_time(java.sql.Date remove_time) {
        this.remove_time = remove_time;
    }
}
