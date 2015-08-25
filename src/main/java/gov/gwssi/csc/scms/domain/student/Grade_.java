package gov.gwssi.csc.scms.domain.student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grade.class)
public abstract class Grade_ {

    public static volatile SingularAttribute<Grade, String> updateBy;
    public static volatile SingularAttribute<Grade, String> course;
    public static volatile SingularAttribute<Grade, String> id;
    public static volatile SingularAttribute<Grade, Integer> annual;
    public static volatile SingularAttribute<Grade, Student> student;
    public static volatile SingularAttribute<Grade, String> term;
    public static volatile SingularAttribute<Grade, String> studentId;
    public static volatile SingularAttribute<Grade, String> grade;
    public static volatile SingularAttribute<Grade, Date> createDate;
    public static volatile SingularAttribute<Grade, String> createBy;
    public static volatile SingularAttribute<Grade, Date> updateDate;

}

