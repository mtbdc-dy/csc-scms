package gov.gwssi.csc.scms.domain.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Menu.class)
public abstract class Menu_ {

    public static volatile SingularAttribute<Menu, String> icon;
    public static volatile SingularAttribute<Menu, String> module;
    public static volatile SingularAttribute<Menu, String> menu;
    public static volatile ListAttribute<Menu, Role> role;
    public static volatile ListAttribute<Menu, Menu> children;
    public static volatile SingularAttribute<Menu, String> menuType;
    public static volatile SingularAttribute<Menu, Menu> parent;
    public static volatile SingularAttribute<Menu, String> menuId;

}

