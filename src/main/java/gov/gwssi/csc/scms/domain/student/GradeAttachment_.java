package gov.gwssi.csc.scms.domain.student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GradeAttachment.class)
public abstract class GradeAttachment_ {

    public static volatile SingularAttribute<GradeAttachment, String> id;
    public static volatile SingularAttribute<GradeAttachment, Student> student;
    public static volatile SingularAttribute<GradeAttachment, Date> createDate;
    public static volatile SingularAttribute<GradeAttachment, String> type;
    public static volatile SingularAttribute<GradeAttachment, String> createBy;
    public static volatile SingularAttribute<GradeAttachment, String> attachmentUri;

}

