package gov.gwssi.csc.scms.domain.ticket;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ticket.class)
public abstract class Ticket_ {

    public static volatile SingularAttribute<Ticket, String> updateBy;
    public static volatile SingularAttribute<Ticket, String> remark;
    public static volatile SingularAttribute<Ticket, String> state;
    public static volatile SingularAttribute<Ticket, String> type;
    public static volatile SingularAttribute<Ticket, String> createBy;
    public static volatile SingularAttribute<Ticket, String> airLine;
    public static volatile SingularAttribute<Ticket, String> leaveCity;
    public static volatile SingularAttribute<Ticket, Date> applyDate;
    public static volatile SingularAttribute<Ticket, String> id;
    public static volatile SingularAttribute<Ticket, String> ticketNo;
    public static volatile SingularAttribute<Ticket, String> school;
    public static volatile SingularAttribute<Ticket, BigDecimal> price;
    public static volatile SingularAttribute<Ticket, Date> updated;
    public static volatile SingularAttribute<Ticket, Date> created;
    public static volatile SingularAttribute<Ticket, Student> student;
    public static volatile SingularAttribute<Ticket, String> studentId;
    public static volatile SingularAttribute<Ticket, Date> validdate;
    public static volatile SingularAttribute<Ticket, Date> flightDate;

}

