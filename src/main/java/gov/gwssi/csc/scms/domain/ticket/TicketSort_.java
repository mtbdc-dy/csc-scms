package gov.gwssi.csc.scms.domain.ticket;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.Date;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TicketSort.class)
public abstract class TicketSort_ {

    public static volatile SingularAttribute<TicketSort, String> updateBy;
    public static volatile SingularAttribute<TicketSort, String> remark;
    public static volatile SingularAttribute<TicketSort, String> state;
    public static volatile SingularAttribute<TicketSort, String> type;
    public static volatile SingularAttribute<TicketSort, String> createBy;
    public static volatile SingularAttribute<TicketSort, String> airLine;
    public static volatile SingularAttribute<TicketSort, String> leaveCity;
    public static volatile SingularAttribute<TicketSort, Date> applyDate;
    public static volatile SingularAttribute<TicketSort, String> id;
    public static volatile SingularAttribute<TicketSort, String> ticketNo;
    public static volatile SingularAttribute<TicketSort, String> school;
    public static volatile SingularAttribute<TicketSort, BigDecimal> price;
    public static volatile SingularAttribute<TicketSort, Date> updated;
    public static volatile SingularAttribute<TicketSort, Date> created;
    public static volatile SingularAttribute<TicketSort, Student> student;
    public static volatile SingularAttribute<TicketSort, String> studentId;
    public static volatile SingularAttribute<TicketSort, Date> validdate;
    public static volatile SingularAttribute<TicketSort, Date> flightDate;
    public static volatile SingularAttribute<TicketSort, Long> customSort;
    public static volatile SingularAttribute<TicketSort, Date> pervalidDate;
}

