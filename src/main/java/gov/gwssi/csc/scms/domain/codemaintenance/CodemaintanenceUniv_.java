package gov.gwssi.csc.scms.domain.codemaintenance;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CodemaintanenceUniv.class)
public  abstract class CodemaintanenceUniv_ {
    public static volatile SingularAttribute<CodemaintanenceUniv, String> id;
    public static volatile SingularAttribute<CodemaintanenceUniv, String> name;
    public static volatile SingularAttribute<CodemaintanenceUniv, String> enabled;
    public static volatile SingularAttribute<CodemaintanenceUniv, String> fullname;
    public static volatile SingularAttribute<CodemaintanenceUniv, Date> updated;
    public static volatile SingularAttribute<CodemaintanenceUniv, String> tableen;
    public static volatile SingularAttribute<CodemaintanenceUniv, String> parentid;
    public static volatile SingularAttribute<CodemaintanenceUniv, String> type;
    public static volatile SingularAttribute<CodemaintanenceUniv, String> admindept;
    public static volatile SingularAttribute<CodemaintanenceUniv, String> code;
}

