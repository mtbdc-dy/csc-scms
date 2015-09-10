package gov.gwssi.csc.scms.domain.scholarship;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScholarshipX.class)
public abstract class ScholarshipX_ {

    public static volatile SingularAttribute<ScholarshipX, String> schResult;
    public static volatile SingularAttribute<ScholarshipX, String> scholarshipId;
    public static volatile SingularAttribute<ScholarshipX, Date> cscStartTime;
    public static volatile SingularAttribute<ScholarshipX, String> cscrresult_lastyear;
    public static volatile SingularAttribute<ScholarshipX, String> schReason;
    public static volatile SingularAttribute<ScholarshipX, Date> schEndTime;
    public static volatile SingularAttribute<ScholarshipX, String> id;
    public static volatile SingularAttribute<ScholarshipX, String> cscSta;
    public static volatile SingularAttribute<ScholarshipX, String> cscReview;
    public static volatile SingularAttribute<ScholarshipX, String> school;
    public static volatile SingularAttribute<ScholarshipX, String> schReview;
    public static volatile SingularAttribute<ScholarshipX, String> cscResult;
    public static volatile SingularAttribute<ScholarshipX, String> schoolSta;
    public static volatile SingularAttribute<ScholarshipX, String> cscReason;
    public static volatile SingularAttribute<ScholarshipX, String> studentId;
    public static volatile SingularAttribute<ScholarshipX, Date> cscEndTime;
    public static volatile SingularAttribute<ScholarshipX, Long> year;
    public static volatile SingularAttribute<ScholarshipX, Date> schStartTime;
    public static volatile SingularAttribute<ScholarshipX, Long> schoolQual;
    public static volatile SingularAttribute<ScholarshipX, Long> cscQual;
    public static volatile SingularAttribute<ScholarshipX, Long> cscUnQual;
    public static volatile SingularAttribute<ScholarshipX, Long> schoolUnQual;
    public static volatile SingularAttribute<ScholarshipX, Date> updated;
    public static volatile SingularAttribute<ScholarshipX, String> updateby;
    public static volatile SingularAttribute<ScholarshipX, String> passportName;
    public static volatile SingularAttribute<ScholarshipX, Student> student;

}

