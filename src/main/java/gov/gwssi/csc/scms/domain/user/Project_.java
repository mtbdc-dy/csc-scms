package gov.gwssi.csc.scms.domain.user;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Project.class)
public abstract class Project_ {

    public static volatile SingularAttribute<Project, String> enabled;
    public static volatile ListAttribute<Project, Project> children;
    public static volatile SingularAttribute<Project, Project> parent;
    public static volatile SingularAttribute<Project, String> nameCH;
    public static volatile SingularAttribute<Project, String> nameEN;
    public static volatile SingularAttribute<Project, String> projectId;
    public static volatile SingularAttribute<Project, String> type;

}

