package gov.gwssi.csc.scms.domain.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Node.class)
public abstract class Node_ {

    public static volatile SingularAttribute<Node, String> enabled;
    public static volatile SingularAttribute<Node, String> phone;
    public static volatile SingularAttribute<Node, Node> parent;
    public static volatile ListAttribute<Node, Node> children;
    public static volatile SingularAttribute<Node, String> nodeEN;
    public static volatile SingularAttribute<Node, String> contacts;
    public static volatile SingularAttribute<Node, String> nodeId;
    public static volatile SingularAttribute<Node, String> node;
    public static volatile SingularAttribute<Node, String> address;
    public static volatile SingularAttribute<Node, String> email;
    public static volatile SingularAttribute<Node, String> nodeLevel;
    public static volatile SingularAttribute<Node, String> collegeHome;
    public static volatile SingularAttribute<Node, String> nodeType;

}

