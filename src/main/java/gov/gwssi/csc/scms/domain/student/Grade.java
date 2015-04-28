package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 学生成绩
 */
@Entity
@Table(name = "SCMS_GRADE")
public class Grade {
    @Id
    private String id;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * 年度
     */
    private String annual;
    /**
     * 学期
     */
    private String term;
    /**
     * 课程
     */
    private String course;
    /**
     * 成绩
     */
    private String grade;
    /**
     * 学生id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCscId() {
        return cscId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
