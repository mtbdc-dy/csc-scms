package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**
 * 来华前概况
 */
@Entity
@Table(name = "SCMS_PROFILES_HISTORY")
public class ProfilesHistory {
    @Id
    private String id;
    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 职业
     */
    private String occupation;
    /**
     * 母语
     */
    private String nativeLanguage;
    /**
     * 体检
     */
    private String phyExam;
    /**
     * 汉语水平
     */
    private String chnLevel;
    /**
     * 英语水平
     */
    private String engLevel;
    /**
     * 学历
     */
    private String educated;
    /**
     * 可否英语授课
     */
    private String engTeach;
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

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getPhyExam() {
        return phyExam;
    }

    public void setPhyExam(String phyExam) {
        this.phyExam = phyExam;
    }

    public String getChnLevel() {
        return chnLevel;
    }

    public void setChnLevel(String chnLevel) {
        this.chnLevel = chnLevel;
    }

    public String getEngLevel() {
        return engLevel;
    }

    public void setEngLevel(String engLevel) {
        this.engLevel = engLevel;
    }

    public String getEducated() {
        return educated;
    }

    public void setEducated(String educated) {
        this.educated = educated;
    }

    public String getEngTeach() {
        return engTeach;
    }

    public void setEngTeach(String engTeach) {
        this.engTeach = engTeach;
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
