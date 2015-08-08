package gov.gwssi.csc.scms.domain.student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Accident.class)
public abstract class Accident_ {

    public static volatile SingularAttribute<Accident, String> summary;
    public static volatile SingularAttribute<Accident, String> updateBy;
    public static volatile SingularAttribute<Accident, String> reason;
    public static volatile SingularAttribute<Accident, String> responsibilityType;
    public static volatile SingularAttribute<Accident, String> state;
    public static volatile SingularAttribute<Accident, String> type;
    public static volatile SingularAttribute<Accident, String> createBy;
    public static volatile SingularAttribute<Accident, Date> updateDate;
    public static volatile SingularAttribute<Accident, String> id;
    public static volatile SingularAttribute<Accident, Student> student;
    public static volatile SingularAttribute<Accident, Date> happenTime;
    public static volatile SingularAttribute<Accident, Date> createDate;
    public static volatile SingularAttribute<Accident, String> happenAddress;

}

