package gov.gwssi.csc.scms.domain.warning;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Warning.class)
public abstract class Warning_ {

    public static volatile SingularAttribute<Warning, String> warningId;
    public static volatile SingularAttribute<Warning, String> addReason;
    public static volatile SingularAttribute<Warning, String> rmUserName;
    public static volatile SingularAttribute<Warning, Date> rmTime;
    public static volatile SingularAttribute<Warning, String> studentId;
    public static volatile SingularAttribute<Warning, String> addUserId;
    public static volatile SingularAttribute<Warning, String> rmUserId;
    public static volatile SingularAttribute<Warning, String> rmReason;
    public static volatile SingularAttribute<Warning, String> addUserName;
    public static volatile SingularAttribute<Warning, Date> addTime;

}

