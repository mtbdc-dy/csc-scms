package gov.gwssi.csc.scms.domain.student;


import javax.persistence.*;

/**
 * 校友信息
 */

@Entity
@Table(name = "SCMS_SCHOOL_FELLOW")
public class SchoolFellow {
    @Id
    @SequenceGenerator(name = "SCHOOL_FELLOW_ID", sequenceName = "SCMS_SCHOOL_FELLOW_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "SCHOOL_FELLOW_ID", strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 职位
     */
    private String position;
    /**
     * 办公电话
     */
    private String officePhone;
    /**
     * 手机
     */
    private String telephone;
    /**
     * 电子邮箱地址
     */
    private String email;
    /**
     * 单位地址
     */
    private String officeAddress;
    /**
     * 回国情况
     */
    private String backSituation;
    /**
     * 成就信息
     */
    private String achievementInfo;
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

    public String getCscId() {
        return cscId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getBackSituation() {
        return backSituation;
    }

    public void setBackSituation(String backSituation) {
        this.backSituation = backSituation;
    }

    public String getAchievementInfo() {
        return achievementInfo;
    }

    public void setAchievementInfo(String achievementInfo) {
        this.achievementInfo = achievementInfo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
