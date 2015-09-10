package gov.gwssi.csc.scms.domain.universities;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/8/31.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DimUniv.class)
public abstract class DimUniv_ {
    public static volatile SingularAttribute<DimUniv, String> univId;
    //public static volatile SingularAttribute<DimUniv, String> univ;
    public static volatile SingularAttribute<DimUniv, String> province;
    public static volatile SingularAttribute<DimUniv,String> newSetby;
    public static volatile SingularAttribute<DimUniv, Date> newBegin;
    public static volatile SingularAttribute<DimUniv, Date> newEnd;
    public static volatile SingularAttribute<DimUniv, Date> newSeted;
    public static volatile SingularAttribute<DimUniv,String> oldSetby;
    public static volatile SingularAttribute<DimUniv, Date> oldBegin;
    public static volatile SingularAttribute<DimUniv, Date> oldEnd;
    public static volatile SingularAttribute<DimUniv, Date> oldSeted;
}
