package gov.gwssi.csc.scms.domain.student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RelatedAddress.class)
public abstract class RelatedAddress_ {

    public static volatile SingularAttribute<RelatedAddress, String> updateBy;
    public static volatile SingularAttribute<RelatedAddress, String> phone;
    public static volatile SingularAttribute<RelatedAddress, String> fax;
    public static volatile SingularAttribute<RelatedAddress, String> remark;
    public static volatile SingularAttribute<RelatedAddress, String> type;
    public static volatile SingularAttribute<RelatedAddress, String> addressOrName;
    public static volatile SingularAttribute<RelatedAddress, String> createBy;
    public static volatile SingularAttribute<RelatedAddress, Date> updateDate;
    public static volatile SingularAttribute<RelatedAddress, String> id;
    public static volatile SingularAttribute<RelatedAddress, String> nature;
    public static volatile SingularAttribute<RelatedAddress, Student> student;
    public static volatile SingularAttribute<RelatedAddress, String> email;
    public static volatile SingularAttribute<RelatedAddress, Date> createDate;

}

