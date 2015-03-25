package gov.gwssi.csc.scms.domain;


import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WangZishi on 3/25/2015.
 */

@Entity
@NamedQuery(name = "allStudent", query = "from Student ")
public class Student implements Serializable{

    @Id
    private String cscNumber;
    private String certificateNumber;
    private String name;
    private String gender;

    public String getCscNumber() {
        return cscNumber;
    }

    public void setCscNumber(String cscNumber) {
        this.cscNumber = cscNumber;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){
        return String.format(
                "Student[cscNumber=%s, " +
                        "certificateNumber=%s, " +
                        "name=%s, " +
                        "gender=%s]",
                cscNumber, certificateNumber, name, gender);
    }
}
