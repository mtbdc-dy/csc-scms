package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 基本信息
 */
@Entity
@Table(name = "SCMS_BASIC_INFO")
public class BasicInfo {
    @Id
    @SequenceGenerator(name = "SCMS_BASIC_INFO_ID", sequenceName = "SCMS_BASIC_INFO_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "SCMS_BASIC_INFO_ID", strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * 年度
     */
    private Integer annual;
    /**
     * 护照姓名
     */
    private String passportName;
    /**
     * 中文姓名
     */
    private String chineseName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 大洲
     */
    private String continent;
    /**
     * 国籍
     */
    private String country;
    /**
     * 图片地址
     */
    private String photo;
    /**
     * 是否计划内 0否；1是
     */
    private Boolean planned;
    /**
     * 留学项目类别
     */
    private String projectType;
    /**
     * 留学项目名称
     */
    private String projectName;
    /**
     * 派遣途径
     */
    private String dispatch;
    /**
     * 国际旅费
     */
    private String travelType;
    /**
     * 学生
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENTID")
    private Student student;

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

    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean isPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
