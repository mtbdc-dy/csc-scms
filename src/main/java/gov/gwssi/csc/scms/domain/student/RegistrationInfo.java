package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 相关地址
 */

@Entity
@Table(name = "SCMS_REGISTRATION_INFO")
public class RegistrationInfo {
    @Id
    @SequenceGenerator(name = "SCMS_REGISTRATION_ID", sequenceName = "SCMS_REGISTRATION_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "SCMS_REGISTRATION_ID", strategy = GenerationType.SEQUENCE)
    private Long id;
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
     * 省市1
     */
    private String province1;
    /**
     * 省市2
     */
    private String province2;
    /**
     * 省市3
     */
    private String province3;
    /**
     * 院校1
     */
    private String university1;
    /**
     * 院校2
     */
    private String university2;
    /**
     * 院校3
     */
    private String university3;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
