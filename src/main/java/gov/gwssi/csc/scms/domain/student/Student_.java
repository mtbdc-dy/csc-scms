package gov.gwssi.csc.scms.domain.student;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.warning.Warning;

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
    public static volatile SingularAttribute<Student, Warning> warning;
    public static volatile ListAttribute<Student, Grade> Grades;
    public static volatile ListAttribute<Student, GradeAttachment> gradeAttachment;
    public static volatile ListAttribute<Student, Abnormal> abnormals;
    public static volatile ListAttribute<Student, Ticket> tickets;
    public static volatile ListAttribute<Student, Insurance> insurances;
    public static volatile ListAttribute<Student, ScholarshipX> scholarshipXs;

}

