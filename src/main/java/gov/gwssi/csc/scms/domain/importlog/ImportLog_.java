package gov.gwssi.csc.scms.domain.importlog;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/9/1.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ImportLog.class)
public abstract class ImportLog_ {
    public static volatile SingularAttribute<ImportLog,String> id;
    public static volatile SingularAttribute<ImportLog,String> fileName;
    public static volatile SingularAttribute<ImportLog,Integer> cnt;
    public static volatile SingularAttribute<ImportLog,String> state;
    public static volatile SingularAttribute<ImportLog,String> importclass;
    public static volatile SingularAttribute<ImportLog,String> createBy;
    public static volatile SingularAttribute<ImportLog,String> error;
    public static volatile SingularAttribute<ImportLog, Date> created;
}
