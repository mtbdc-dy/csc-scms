package gov.gwssi.csc.scms.domain.student;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {

    public static volatile ListAttribute<Student, RelatedAddress> relatedAddress;
    public static volatile SingularAttribute<Student, String> id;
    public static volatile SingularAttribute<Student, BasicInfo> basicInfo;
    public static volatile ListAttribute<Student, Accident> accidents;
    public static volatile SingularAttribute<Student, String> cscId;
    public static volatile SingularAttribute<Student, Discuss> discuss;
    public static volatile SingularAttribute<Student, SchoolRoll> schoolRoll;
    public static volatile SingularAttribute<Student, ProfilesHistory> profilesHistory;
    public static volatile SingularAttribute<Student, Schoolfellow> schoolfellow;
    public static volatile SingularAttribute<Student, RegistrationInfo> registrationInfo;
    public static volatile ListAttribute<Student, Grade> Grades;
    public static volatile ListAttribute<Student, GradeAttachment> gradeAttachment;

}

