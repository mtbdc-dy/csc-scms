package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**基本信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_BASIC_INFO")
public class BasicInfo {
    @Id private Long id;

    private String remark;
    private int year;
    private String continent_name;
    private java.sql.Date update_date;
    private String birthday;
    private String travel_name;
    private String nature_places;
    private String gender;
    private String country_name;
    private String update_by;
    private String dispatch_name;
    private String project_name;
    private String project_type_name;
    private String passport_name;
    private String chinese_name;

    @OneToOne private Student student;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getContinent_name() {
        return continent_name;
    }

    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
    }

    public java.sql.Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(java.sql.Date update_date) {
        this.update_date = update_date;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTravel_name() {
        return travel_name;
    }

    public void setTravel_name(String travel_name) {
        this.travel_name = travel_name;
    }

    public String getNature_places() {
        return nature_places;
    }

    public void setNature_places(String nature_places) {
        this.nature_places = nature_places;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getDispatch_name() {
        return dispatch_name;
    }

    public void setDispatch_name(String dispatch_name) {
        this.dispatch_name = dispatch_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_type_name() {
        return project_type_name;
    }

    public void setProject_type_name(String project_type_name) {
        this.project_type_name = project_type_name;
    }

    public String getPassport_name() {
        return passport_name;
    }

    public void setPassport_name(String passport_name) {
        this.passport_name = passport_name;
    }

    public String getChinese_name() {
        return chinese_name;
    }

    public void setChinese_name(String chinese_name) {
        this.chinese_name = chinese_name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
