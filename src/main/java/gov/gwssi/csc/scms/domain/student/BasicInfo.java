package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 基本信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_BASIC_INFO")
public class BasicInfo {
    @Id
    private Long id;
    @Column(columnDefinition = "varchar2(200)")
    private Integer annual; //年度
    private String passport_name;//护照姓名
    /**
     * 中文姓名
     */
    private String chinese_name;//中文姓名
    private String gender;//性别
    private String birthday;//出生日期
    private String continent_name;//洲别
    private String country_name;//国籍
    private String photo_uri;//照片uri
    private Boolean planned;//计划内外 1计划内 0计划外
    private String project_type;//项目类别
    private String project_name;//项目名称
    private String dispatch;//派遣途径
    private String travel_type;//国际旅费

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnual() {
        return annual;
    }

    public void setAnnual(Integer annual) {
        this.annual = annual;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getContinent_name() {
        return continent_name;
    }

    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getPhoto_uri() {
        return photo_uri;
    }

    public void setPhoto_uri(String photo_uri) {
        this.photo_uri = photo_uri;
    }

    public Boolean isPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getTravel_type() {
        return travel_type;
    }

    public void setTravel_type(String travel_type) {
        this.travel_type = travel_type;
    }
}
