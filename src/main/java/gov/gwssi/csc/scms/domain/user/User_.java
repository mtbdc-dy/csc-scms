package gov.gwssi.csc.scms.domain.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

    public static volatile SingularAttribute<User, String> updateBy;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> fax;
    public static volatile SingularAttribute<User, String> enable;
    public static volatile SingularAttribute<User, String> answer;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> createBy;
    public static volatile SingularAttribute<User, Date> updateDate;
    public static volatile SingularAttribute<User, String> userType;
    public static volatile SingularAttribute<User, String> id;
    public static volatile ListAttribute<User, Project> projects;
    public static volatile SingularAttribute<User, Node> node;
    public static volatile SingularAttribute<User, String> userId;
    public static volatile SingularAttribute<User, Role> role;
    public static volatile SingularAttribute<User, String> fullName;
    public static volatile SingularAttribute<User, String> question;
    public static volatile SingularAttribute<User, Date> createDate;

}

