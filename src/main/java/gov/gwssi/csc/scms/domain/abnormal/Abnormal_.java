package gov.gwssi.csc.scms.domain.abnormal;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Abnormal.class)
public abstract class Abnormal_ {

    public static volatile SingularAttribute<Abnormal, String> updateBy;
    public static volatile SingularAttribute<Abnormal, String> applyUri;
    public static volatile SingularAttribute<Abnormal, String> reasonId;
    public static volatile SingularAttribute<Abnormal, String> reason;
    public static volatile SingularAttribute<Abnormal, String> approOpinion;
    public static volatile SingularAttribute<Abnormal, String> state;
    public static volatile SingularAttribute<Abnormal, String> approUserName;
    public static volatile SingularAttribute<Abnormal, Date> applyTime;
    public static volatile SingularAttribute<Abnormal, String> handleUserName;
    public static volatile SingularAttribute<Abnormal, String> publicUri;
    public static volatile SingularAttribute<Abnormal, Date> handleTime;
    public static volatile SingularAttribute<Abnormal, Date> reportTime;
    public static volatile SingularAttribute<Abnormal, String> id;
    public static volatile SingularAttribute<Abnormal, String> reasonTypeId;
    public static volatile SingularAttribute<Abnormal, String> applyUserId;
    public static volatile SingularAttribute<Abnormal, Date> updated;
    public static volatile SingularAttribute<Abnormal, Student> student;
    public static volatile SingularAttribute<Abnormal, String> studentId;
    public static volatile SingularAttribute<Abnormal, Date> approTime;
    public static volatile SingularAttribute<Abnormal, String> applyUserName;
    public static volatile SingularAttribute<Abnormal, String> approUserId;
    public static volatile SingularAttribute<Abnormal, String> approResult;
    public static volatile SingularAttribute<Abnormal, String> reportUserName;
    public static volatile SingularAttribute<Abnormal, String> reportUserId;
    public static volatile SingularAttribute<Abnormal, String> handleUserId;

}

