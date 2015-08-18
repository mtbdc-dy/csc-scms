package gov.gwssi.csc.scms.domain.log;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OperationLog.class)
public abstract class OperationLog_ {

    public static volatile SingularAttribute<OperationLog, String> module;
    public static volatile SingularAttribute<OperationLog, String> moduleId;
    public static volatile SingularAttribute<OperationLog, String> before;
    public static volatile SingularAttribute<OperationLog, String> createBy;
    public static volatile SingularAttribute<OperationLog, String> id;
    public static volatile SingularAttribute<OperationLog, String> optType;
    public static volatile SingularAttribute<OperationLog, String> after;
    public static volatile SingularAttribute<OperationLog, String> columnEN;
    public static volatile SingularAttribute<OperationLog, String> columnCH;
    public static volatile SingularAttribute<OperationLog, String> nodeId;
    public static volatile SingularAttribute<OperationLog, String> cscId;
    public static volatile SingularAttribute<OperationLog, String> studentId;
    public static volatile SingularAttribute<OperationLog, Date> createD;
    public static volatile SingularAttribute<OperationLog, String> nodeType;
    public static volatile SingularAttribute<OperationLog, String> passportName;
    public static volatile SingularAttribute<OperationLog, String> tableEN;
    public static volatile SingularAttribute<OperationLog, String> tableCH;

}

