package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**
 * 商议信息
 */

@Entity
@Table(name = "SCMS_DISCUSS")
public class Discuss {
    @Id
    private String id;
    /**
     * 经费办法
     */
    private String appropriation;
    /**
     * 学生类别
     */
    private String studentType;
    /**
     * 授课语言
     */
    private String teachLanguage;
    /**
     * 学科门类
     */
    private String disciplines;
    /**
     * 一级学科
     */
    private String subject;
    /**
     * 专业
     */
    private String major;
    /**
     * 是否汉语补习
     */
    private String chineseCram;
    /**
     * 汉补开始时间
     */
    private Date cramDateBegin;
    /**
     * 汉补结束时间
     */
    private Date cramDateEnd;
    /**
     * 省市1
     */
    private String province1;//省市1
    /**
     * 省市2
     */
    private String province2;//省市2
    /**
     * 省市3
     */
    private String province3;//省市3
    /**
     * 院校1
     */
    private String university1;//院校1
    /**
     * 院校2
     */
    private String university2;//院校2
    /**
     * 院校3
     */
    private String university3;//院校3
    /**
     * 商议状态（1表 2录 3否 4待 5发 6备 7单报）
     */
    private String discussState;
    /**
     * 经办情况
     */
    private String handleStatus;
    /**
     * 备注
     */
    private String remark;
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

    public String getAppropriation() {
        return appropriation;
    }

    public void setAppropriation(String appropriation) {
        this.appropriation = appropriation;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getTeachLanguage() {
        return teachLanguage;
    }

    public void setTeachLanguage(String teachLanguage) {
        this.teachLanguage = teachLanguage;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getChineseCram() {
        return chineseCram;
    }

    public void setChineseCram(String chineseCram) {
        this.chineseCram = chineseCram;
    }

    public Date getCramDateBegin() {
        return cramDateBegin;
    }

    public void setCramDateBegin(Date cramDateBegin) {
        this.cramDateBegin = cramDateBegin;
    }

    public Date getCramDateEnd() {
        return cramDateEnd;
    }

    public void setCramDateEnd(Date cramDateEnd) {
        this.cramDateEnd = cramDateEnd;
    }

    public String getProvince1() {
        return province1;
    }

    public void setProvince1(String province1) {
        this.province1 = province1;
    }

    public String getProvince2() {
        return province2;
    }

    public void setProvince2(String province2) {
        this.province2 = province2;
    }

    public String getProvince3() {
        return province3;
    }

    public void setProvince3(String province3) {
        this.province3 = province3;
    }

    public String getUniversity1() {
        return university1;
    }

    public void setUniversity1(String university1) {
        this.university1 = university1;
    }

    public String getUniversity2() {
        return university2;
    }

    public void setUniversity2(String university2) {
        this.university2 = university2;
    }

    public String getUniversity3() {
        return university3;
    }

    public void setUniversity3(String university3) {
        this.university3 = university3;
    }

    public String getDiscussState() {
        return discussState;
    }

    public void setDiscussState(String discussState) {
        this.discussState = discussState;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
