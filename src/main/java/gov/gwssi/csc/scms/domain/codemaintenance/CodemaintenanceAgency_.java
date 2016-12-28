package gov.gwssi.csc.scms.domain.codemaintenance;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created by TianJ on 2016/12/23.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CodemaintenanceAgency.class)
public class CodemaintenanceAgency_ {
    public static volatile SingularAttribute<CodemaintenanceAgency, String> id;
    public static volatile SingularAttribute<CodemaintenanceAgency, String> name;
    public static volatile SingularAttribute<CodemaintenanceAgency, String> code;
    public static volatile SingularAttribute<CodemaintenanceAgency, String> enabled;
    public static volatile SingularAttribute<CodemaintenanceAgency, String> fullname;
    public static volatile SingularAttribute<CodemaintenanceAgency, Date> updated;
    public static volatile SingularAttribute<CodemaintenanceAgency, String> tableen;
    public static volatile SingularAttribute<CodemaintenanceAgency, String> parentid;
}
