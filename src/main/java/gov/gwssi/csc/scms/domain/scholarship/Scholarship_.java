package gov.gwssi.csc.scms.domain.scholarship;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Scholarship.class)
public abstract class Scholarship_ {

    public static volatile SingularAttribute<Scholarship, String> updateBy;
    public static volatile SingularAttribute<Scholarship, Date> cscDate;
    public static volatile SingularAttribute<Scholarship, String> createBy;
    public static volatile SingularAttribute<Scholarship, Date> schoolDate;
    public static volatile SingularAttribute<Scholarship, String> id;
    public static volatile SingularAttribute<Scholarship, String> cscSta;
    public static volatile SingularAttribute<Scholarship, String> school;
    public static volatile SingularAttribute<Scholarship, String> schoolSta;
    public static volatile SingularAttribute<Scholarship, Date> created;
    public static volatile SingularAttribute<Scholarship, Date> updated;
    public static volatile SingularAttribute<Scholarship, Long> year;
    public static volatile SingularAttribute<Scholarship, Long> schoolQual;
    public static volatile SingularAttribute<Scholarship, Long> cscQual;
    public static volatile SingularAttribute<Scholarship, Long> cscUnQual;
    public static volatile SingularAttribute<Scholarship, Long> schoolUnQual;
}

