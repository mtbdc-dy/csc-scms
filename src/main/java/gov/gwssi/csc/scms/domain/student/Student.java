package gov.gwssi.csc.scms.domain.student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "LHLX_STUDENT")
public class Student {
    @Id private String cscNumber ;
    @OneToOne private BasicInfo basicInfo;
    @OneToOne private RegistrationInfo registrationInfo;
    @OneToOne private Discuss discuss;
    @OneToOne private SchoolRoll schoolRoll;
    @OneToOne private RelatedAddress relatedAddress;
    @OneToOne private Accident accident;

    public String getCscNumber() {
        return cscNumber;
    }

    public void setCscNumber(String cscNumber) {
        this.cscNumber = cscNumber;
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

    public RelatedAddress getRelatedAddress() {
        return relatedAddress;
    }

    public void setRelatedAddress(RelatedAddress relatedAddress) {
        this.relatedAddress = relatedAddress;
    }

    public Accident getAccident() {
        return accident;
    }

    public void setAccident(Accident accident) {
        this.accident = accident;
    }
}
