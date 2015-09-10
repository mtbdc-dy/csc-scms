package gov.gwssi.csc.scms.domain.scholarship;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScholarshipDetail.class)
public abstract class ScholarshipDetail_ {

    public static volatile SingularAttribute<ScholarshipDetail, String> schResult;
    public static volatile SingularAttribute<ScholarshipDetail, Scholarship> scholarship;
    public static volatile SingularAttribute<ScholarshipDetail, Date> cscStartTime;
    public static volatile SingularAttribute<ScholarshipDetail, String> schReason;
    public static volatile SingularAttribute<ScholarshipDetail, Date> schEndTime;
    public static volatile SingularAttribute<ScholarshipDetail, String> id;
    public static volatile SingularAttribute<ScholarshipDetail, String> cscReview;
    public static volatile SingularAttribute<ScholarshipDetail, String> schReview;
    public static volatile SingularAttribute<ScholarshipDetail, String> cscResult;
    public static volatile SingularAttribute<ScholarshipDetail, String> cscReason;
    public static volatile SingularAttribute<ScholarshipDetail, String> studentId;
    public static volatile SingularAttribute<ScholarshipDetail, Date> cscEndTime;
    public static volatile SingularAttribute<ScholarshipDetail, Date> schStartTime;
    public static volatile SingularAttribute<ScholarshipDetail, Student> student;

}

