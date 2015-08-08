package gov.gwssi.csc.scms.domain.student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProfilesHistory.class)
public abstract class ProfilesHistory_ {

    public static volatile SingularAttribute<ProfilesHistory, String> updateBy;
    public static volatile SingularAttribute<ProfilesHistory, String> occupation;
    public static volatile SingularAttribute<ProfilesHistory, String> engLevel;
    public static volatile SingularAttribute<ProfilesHistory, String> workUnit;
    public static volatile SingularAttribute<ProfilesHistory, String> engTeach;
    public static volatile SingularAttribute<ProfilesHistory, String> createBy;
    public static volatile SingularAttribute<ProfilesHistory, Date> updateDate;
    public static volatile SingularAttribute<ProfilesHistory, String> id;
    public static volatile SingularAttribute<ProfilesHistory, String> nativeLanguage;
    public static volatile SingularAttribute<ProfilesHistory, Student> student;
    public static volatile SingularAttribute<ProfilesHistory, String> phyExam;
    public static volatile SingularAttribute<ProfilesHistory, String> educated;
    public static volatile SingularAttribute<ProfilesHistory, Date> createDate;
    public static volatile SingularAttribute<ProfilesHistory, String> chnLevel;

}

