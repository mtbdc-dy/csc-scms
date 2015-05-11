package gov.gwssi.csc.scms.domain.student;

import gov.gwssi.csc.scms.utils.UnicodeUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * 基本信息
 */
@Entity
@Table(name = "SCMS_BASIC_INFO")
public class BasicInfo {
    @Id
    private String id;
    /**
     * 年度
     */
    private int annual;
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
    private String gender;
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
     * 学生
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAnnual() {
        return annual;
    }

    public void setAnnual(int annual) {
        this.annual = annual;
    }

    public String getPassportName() {
        return UnicodeUtil.toCharSequence(passportName);
    }

    public void setPassportName(String passportName) {
        this.passportName = UnicodeUtil.toUNICODE(passportName);
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
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
