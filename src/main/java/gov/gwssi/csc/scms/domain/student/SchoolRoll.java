package gov.gwssi.csc.scms.domain.student;

import javax.persistence.Entity;
import javax.persistence.Table;

/**学籍信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "LHLX_SCHOOLROLL")
public class SchoolRoll {
    private String state;
    private String state_jjw;
    private java.sql.Date time;
    private String user_id;
    private String id;
    private String user_name;
    private String csc_id;
    private String year;
    private String school;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_jjw() {
        return state_jjw;
    }

    public void setState_jjw(String state_jjw) {
        this.state_jjw = state_jjw;
    }

    public java.sql.Date getTime() {
        return time;
    }

    public void setTime(java.sql.Date time) {
        this.time = time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
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
}
