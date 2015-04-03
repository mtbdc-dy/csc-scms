package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Wang Rui on 2015/3/30.
 *
 */
@Entity
@Table(name = "SCMS_STUDENT")
public class Student {
//    @Id private Long id;
    @Id private String csc_id;
    @OneToOne private BasicInfo basicInfo;
    @OneToOne private RegistrationInfo registrationInfo;
    @OneToOne private Discuss discuss;
    @OneToOne private SchoolRoll schoolRoll;
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="student")
    private List<RelatedAddress> relatedAddress;
    @OneToMany
    private List<Accident> accident;

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public RegistrationInfo getRegistrationInfo() {
        return registrationInfo;
    }

    public void setRegistrationInfo(RegistrationInfo registrationInfo) {
        this.registrationInfo = registrationInfo;
    }

    public Discuss getDiscuss() {
        return discuss;
    }

    public void setDiscuss(Discuss discuss) {
        this.discuss = discuss;
    }

    public SchoolRoll getSchoolRoll() {
        return schoolRoll;
    }

    public void setSchoolRoll(SchoolRoll schoolRoll) {
        this.schoolRoll = schoolRoll;
    }

    public List<RelatedAddress> getRelatedAddress() {
        return relatedAddress;
    }

    public void setRelatedAddress(List<RelatedAddress> relatedAddress) {
        this.relatedAddress = relatedAddress;
    }

    public List<Accident> getAccident() {
        return accident;
    }

    public void setAccident(List<Accident> accident) {
        this.accident = accident;
    }
}
