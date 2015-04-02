package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 来华前概况、申请信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_REGISTRATION_INFO")
public class RegistrationInfo {
    @Id
    private Long id;
    @OneToOne
    private Student student;
    private String stu_type;//学生类别
    private String teach_language;//授课语言
    private String disciplines;//学科门类
    private String subject;//一级学科
    private String major;//专业
    private String province_1;//省市1
    private String province_2;//省市2
    private String province_3;//省市3
    private String university_1;//院校1
    private String university_2;//院校2
    private String university_3;//院校3

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStu_type() {
        return stu_type;
    }

    public void setStu_type(String stu_type) {
        this.stu_type = stu_type;
    }

    public String getTeach_language() {
        return teach_language;
    }

    public void setTeach_language(String teach_language) {
        this.teach_language = teach_language;
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

    public String getProvince_1() {
        return province_1;
    }

    public void setProvince_1(String province_1) {
        this.province_1 = province_1;
    }

    public String getProvince_2() {
        return province_2;
    }

    public void setProvince_2(String province_2) {
        this.province_2 = province_2;
    }

    public String getProvince_3() {
        return province_3;
    }

    public void setProvince_3(String province_3) {
        this.province_3 = province_3;
    }

    public String getUniversity_1() {
        return university_1;
    }

    public void setUniversity_1(String university_1) {
        this.university_1 = university_1;
    }

    public String getUniversity_2() {
        return university_2;
    }

    public void setUniversity_2(String university_2) {
        this.university_2 = university_2;
    }

    public String getUniversity_3() {
        return university_3;
    }

    public void setUniversity_3(String university_3) {
        this.university_3 = university_3;
    }
}
