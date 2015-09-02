package gov.gwssi.csc.scms.domain.scholarship;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by tianj on 2015/9/1.
 */
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScholarshipJ.class)
public abstract class ScholarshipJ_ {
    public static volatile SingularAttribute<ScholarshipJ, Long> id;
    public static volatile SingularAttribute<ScholarshipJ, String> scholarshipId;
    public static volatile SingularAttribute<ScholarshipJ, Long> year;
    public static volatile SingularAttribute<ScholarshipJ, Character> state;
    public static volatile SingularAttribute<ScholarshipJ, String> province;
    public static volatile SingularAttribute<ScholarshipJ, String> univ;
    public static volatile SingularAttribute<ScholarshipJ, String> schooldate;
    public static volatile SingularAttribute<ScholarshipJ, String> qualified;
    public static volatile SingularAttribute<ScholarshipJ, Long> count;
    public static volatile SingularAttribute<ScholarshipJ, Long> schoolQual;
    public static volatile SingularAttribute<ScholarshipJ, Long> schoolUnQual;
    public static volatile SingularAttribute<ScholarshipJ, Long> cscQual;
    public static volatile SingularAttribute<ScholarshipJ, Long> cscUnQual;
    public static volatile SingularAttribute<ScholarshipJ, String> cscdate;


}
