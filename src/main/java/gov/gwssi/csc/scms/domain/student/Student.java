package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_STUDENT")
public class Student {
    @Id
    private String csc_id;
    @OneToOne
    @JoinColumn(name = "basicInfo", unique = true, nullable = false)
    private BasicInfo basicInfo;
    @OneToOne
    @JoinColumn(name = "profilesHistory")
    private ProfilesHistory profilesHistory;
    @OneToOne
    @JoinColumn(name = "registrationInfo", unique = true, nullable = false)
    private RegistrationInfo registrationInfo;
    @OneToOne
    @JoinColumn(name = "discuss", unique = true)
    private Discuss discuss;
    @OneToOne
    @JoinColumn(name = "schoolRoll", unique = true, nullable = false)
    private SchoolRoll schoolRoll;
    @OneToMany(mappedBy = "student")
    private List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
    @OneToMany(mappedBy = "student")
    private List<Accident> accidents = new ArrayList<Accident>();

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
        return accidents;
    }

    public void setAccident(List<Accident> accident) {
        this.accidents = accident;
    }

    public ProfilesHistory getProfilesHistory() {
        return profilesHistory;
    }

    public void setProfilesHistory(ProfilesHistory profilesHistory) {
        this.profilesHistory = profilesHistory;
    }
}
