package gov.gwssi.csc.scms.domain.insurance;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Insurance.class)
public abstract class Insurance_ {

    public static volatile SingularAttribute<Insurance, String> id;
    public static volatile SingularAttribute<Insurance, String> insurNo;
    public static volatile SingularAttribute<Insurance, Date> updated;
    public static volatile SingularAttribute<Insurance, Date> created;
    public static volatile SingularAttribute<Insurance, String> studentId;
    public static volatile SingularAttribute<Insurance, String> preSta;
    public static volatile SingularAttribute<Insurance, String> userName;
    public static volatile SingularAttribute<Insurance, Long> year;
    public static volatile SingularAttribute<Insurance, String> insurSta;
    public static volatile SingularAttribute<Insurance, String> createBy;

}

