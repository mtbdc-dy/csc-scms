package gov.gwssi.csc.scms.domain;

//import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by WangZishi on 3/25/2015.
 *
 */
@Entity
//@Table
public class Warning {

    @Id
    @GeneratedValue
    private int id;

    private String reasonToAdd;
    private String addedTime;

    @OneToOne
//    @JoinColumn()
    private StudentWzs student;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    // @JoinColumn(name = "student_id")
//    public Student getStudent() {
//        return student;
//    }

//    public void setStudent(Student student) {
//        this.student = student;
//    }

    public String getReasonToAdd() {
        return reasonToAdd;
    }

    public void setReasonToAdd(String reasonToAdd) {
        this.reasonToAdd = reasonToAdd;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }
}
