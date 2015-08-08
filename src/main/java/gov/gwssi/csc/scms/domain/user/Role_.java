package gov.gwssi.csc.scms.domain.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

    public static volatile SingularAttribute<Role, String> updateBy;
    public static volatile ListAttribute<Role, Menu> menus;
    public static volatile SingularAttribute<Role, String> identity;
    public static volatile SingularAttribute<Role, String> enable;
    public static volatile SingularAttribute<Role, String> role;
    public static volatile SingularAttribute<Role, Date> createDate;
    public static volatile SingularAttribute<Role, String> createBy;
    public static volatile SingularAttribute<Role, Date> updateDate;
    public static volatile SingularAttribute<Role, String> roleId;

}

