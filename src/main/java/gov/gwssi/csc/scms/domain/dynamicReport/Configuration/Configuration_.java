package gov.gwssi.csc.scms.domain.dynamicReport.Configuration;

import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Calendar;
/**
 * Created by wangzishi on 15/10/12.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Configuration.class)
public class Configuration_ {
    public static volatile SingularAttribute<Configuration, String> id;
    public static volatile SingularAttribute<Configuration, String> title;
    public static volatile SingularAttribute<Configuration, String> reportType;
    public static volatile SingularAttribute<Configuration, String> description;
    public static volatile SingularAttribute<Configuration, String> accessState;
    public static volatile SingularAttribute<Configuration, String> rawConfig;
    public static volatile SetAttribute<Configuration, JoinCondition> joinCondition;
    public static volatile ListAttribute<Configuration, WhereCondition> whereCondition;
    public static volatile SetAttribute<Configuration, GroupCondition> groupCondition;
    public static volatile SetAttribute<Configuration, OrderCondition> orderCondition;
    public static volatile ListAttribute<Configuration, SelectCondition> selectCondition;
    public static volatile ListAttribute<Configuration, Cell> cells;
    public static volatile SingularAttribute<Configuration, Calendar> created;
    public static volatile SingularAttribute<Configuration, String> createBy;
    public static volatile SingularAttribute<Configuration, Calendar> updated;
    public static volatile SingularAttribute<Configuration, String> updateBy;
}


