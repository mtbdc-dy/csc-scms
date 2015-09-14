package gov.gwssi.csc.scms.domain.codemaintenance;

import gov.gwssi.csc.scms.domain.log.OperationLog;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by LiZhiSheng on 2015/9/10.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CodeMainTenance.class)
public  abstract class CodeMainTenance_ {
    public static volatile SingularAttribute<CodeMainTenance, Integer> seq;
    public static volatile SingularAttribute<CodeMainTenance, String> tableEn;
    public static volatile SingularAttribute<CodeMainTenance, Character> flag;
    public static volatile SingularAttribute<CodeMainTenance, String> tableCh;
    public static volatile SingularAttribute<CodeMainTenance, String> category;
    public static volatile SingularAttribute<CodeMainTenance, String> type;
    public static volatile SingularAttribute<CodeMainTenance, String> sql;

}
