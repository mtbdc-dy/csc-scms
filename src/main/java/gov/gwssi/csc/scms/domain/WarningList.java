package gov.gwssi.csc.scms.domain;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Entity
@Table
public class WarningList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private Student student;
    private String reasonToAdd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getReasonToAdd() {
        return reasonToAdd;
    }

    public void setReasonToAdd(String reasonToAdd) {
        this.reasonToAdd = reasonToAdd;
    }
}
