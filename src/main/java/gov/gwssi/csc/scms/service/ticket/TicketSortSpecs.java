package gov.gwssi.csc.scms.service.ticket;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.ticket.TicketSort;
import gov.gwssi.csc.scms.domain.ticket.TicketSort_;
import gov.gwssi.csc.scms.domain.ticket.Ticket_;
import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.utils.DateConvert;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tianj on 2015/8/29.
 */
public class TicketSortSpecs extends BaseService {
    public static Specification<TicketSort> filterIsLike(final Filter filter, final User user) {
        return new Specification<TicketSort>() {
            @Override
            public Predicate toPredicate(Root<TicketSort> ticketSortRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();


                boolean needBasicInfo = filter.getPassportName() != null
                        || filter.getContinent() != null
                        || filter.getCountry() != null
//                        || filter.getProjectAttr() != null
                        || filter.getProjectType() != null
                        || filter.getProjectName() != null
                        || filter.getPlanned() != null
                        || filter.getDispatchType() != null
                        || filter.getDispatch() != null
                        || filter.getTravelType() != null
                        || filter.getAnnual() != null;
                boolean needSchoolRoll = filter.getRegisterState() != null
                        || filter.getStudentType() != null
                        || filter.getFundType() != null
                        || filter.getTeachLanguage() != null
                        || filter.getSchoolRollState() != null
                        || filter.getArrivalDateBegin() != null
                        || filter.getArrivalDateEnd() != null
                        || filter.getLeaveDateBegin() != null
                        || filter.getLeaveDateEnd() != null
                        || filter.getCramDateBeginBegin() != null
                        || filter.getCramDateBeginEnd() != null
                        || filter.getCramDateEndBegin() != null
                        || filter.getCramDateEndEnd() != null
                        || filter.getMajorStartDateBegin() != null
                        || filter.getMajorStartDateEnd() != null
                        || filter.getPlanLeaveDateBegin() != null
                        || filter.getPlanLeaveDateEnd() != null
                        || filter.getCurrentUniversity() != null
                        || filter.getCurrentProvince() != null;
                boolean needStudent = filter.getCscId() != null
                        || needBasicInfo || needSchoolRoll;


                /**机票部分*/
                Date date_1 = null;
                Date date_2 = null;
                Date date_3 = null;
                Date date_4 = null;
                Date intialDate = null;
                Date finalDate = null;
                int currentYear=0;
                int preYear = 0;
                int nextYear = 0;
                try {
                    Calendar calendar = Calendar.getInstance();
                    currentYear = calendar.get(Calendar.YEAR);
                    preYear = currentYear-1;
                    SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
                    date_1 = ds.parse(currentYear + "-01-01");
                    date_2 = ds.parse(currentYear + "-03-01");
                    date_3 = ds.parse(currentYear + "-09-01");
                    date_4 = ds.parse(currentYear + "-12-31");
                    if((calendar.after(date_1) || calendar.equals(date_1)) && calendar.before(date_2)){
                        intialDate = ds.parse(preYear + "-09-01");
                        finalDate = date_2;
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(ticketSortRoot.get(TicketSort_.created), intialDate));
                        predicate.getExpressions().add(cb.lessThan(ticketSortRoot.get(TicketSort_.created), finalDate));
                    }else if((calendar.after(date_2) || calendar.equals(date_2)) && calendar.before(date_3)){
                        intialDate = date_2;
                        finalDate = date_3;
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(ticketSortRoot.get(TicketSort_.created), intialDate));
                        predicate.getExpressions().add(cb.lessThan(ticketSortRoot.get(TicketSort_.created), finalDate));
                    }else{
                        intialDate = date_3;
                        finalDate = ds.parse(nextYear + "03-01");
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(ticketSortRoot.get(TicketSort_.created), intialDate));
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(ticketSortRoot.get(TicketSort_.created), finalDate));
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                predicate.getExpressions().add(cb.equal(ticketSortRoot.get(TicketSort_.type), "BF0002"));

                if (filter.getTicketState() != null) {
                    if(filter.getTicketState().equals("AT0006")){
                        predicate.getExpressions().add(cb.in(ticketSortRoot.get(TicketSort_.state)).value("AT0002").value("AT0005"));
                    }else{
                        predicate.getExpressions().add(cb.like(ticketSortRoot.get(TicketSort_.state), filter.getTicketState()));
                    }

                } else {
                    if ("2".equals(user.getUserType())) {//1 基金委用户 2学校用户
                        predicate.getExpressions().add(cb.in(ticketSortRoot.get(TicketSort_.state)).value("AT0001").value("AT0002").value("AT0005").value("AT0003").value("AT0004"));
                    } else if ("1".equals(user.getUserType())) {
                        predicate.getExpressions().add(cb.in(ticketSortRoot.get(TicketSort_.state)).value("AT0002").value("AT0005").value("AT0003").value("AT0004"));
                    }
                }
//                if (filter.getTicketType() != null) {
//                    predicate.getExpressions().add(cb.like(ticketSortRoot.get(TicketSort_.type), filter.getTicketType()));
//
//                }
                /**学生主表部分*/
                if (needStudent) {
                    Join<TicketSort, Student> student = ticketSortRoot.join(TicketSort_.student);
                    if (filter.getCscId() != null) {
                        predicate.getExpressions().add(cb.like(cb.lower(student.get(Student_.cscId)), filter.getCscId().toLowerCase()));
                    }
                    if (needBasicInfo) {
                        Join<Student, BasicInfo> basicInfo = student.join(Student_.basicInfo);
                        /**基本信息部分*/
                        if (filter.getPassportName() != null) {
                            predicate.getExpressions().add(cb.like(cb.lower(basicInfo.get(BasicInfo_.passportName)), filter.getPassportName().toLowerCase()));
                        }
                        if (filter.getContinent() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.continent), filter.getContinent()));
                        }
                        if (filter.getCountry() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.country), filter.getCountry()));
                        }
//                        if (filter.getProjectAttr() != null) {
//                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.projectAttr), filter.getProjectAttr()));
//                        }
                        if (filter.getProjectType() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.projectType), filter.getProjectType()));
                        }
                        if (filter.getProjectName() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.projectName), filter.getProjectName()));
                        }
                        if (filter.getPlanned() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.planned), filter.getPlanned()));
                        }
                        if (filter.getDispatchType() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.dispatchType), filter.getDispatchType()));
                        }
                        if (filter.getDispatch() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.dispatch), filter.getDispatch()));
                        }
                        if (filter.getTravelType() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.travelType), filter.getTravelType()));
                        }
                        if (filter.getAnnual() != null) {
                            predicate.getExpressions().add(cb.equal(basicInfo.get(BasicInfo_.annual), filter.getAnnual()));
                        }
                    }
                    if (needSchoolRoll) {
                        Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                        /**学籍部分*/
                        if (filter.getRegisterState() != null) {
                            predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.registerState), filter.getRegisterState()));
                        }
                        if (filter.getStudentType() != null) {
                            predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.studentType), filter.getStudentType()));
                        }
                        if (filter.getFundType() != null) {
                            predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.appropriation), filter.getFundType()));
                        }
                        if (filter.getTeachLanguage() != null) {
                            predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.teachLanguage), filter.getTeachLanguage()));
                        }
                        if (filter.getSchoolRollState() != null) {
                            predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.state), filter.getSchoolRollState()));
                        }
                        if (filter.getArrivalDateBegin() != null) {
                            Date begin = DateConvert.convert(filter.getArrivalDateBegin(), "begin");
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.arrivalDate), begin));
                        }
                        if (filter.getArrivalDateEnd() != null) {
                            Date end = DateConvert.convert(filter.getArrivalDateEnd(),"end");
                            predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.arrivalDate), end));
                        }
                        if (filter.getLeaveDateBegin() != null) {
                            Date begin = DateConvert.convert(filter.getLeaveDateBegin(),"begin");
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.leaveDate), begin));
                        }
                        if (filter.getLeaveDateEnd() != null) {
                            Date end = DateConvert.convert(filter.getLeaveDateEnd(),"end");
                            predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.leaveDate), end));
                        }
                        if (filter.getCramDateBeginBegin() != null) {
                            Date begin = DateConvert.convert(filter.getCramDateBeginBegin(),"begin");
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateBegin), begin));
                        }
                        if (filter.getCramDateBeginEnd() != null) {
                            Date end = DateConvert.convert(filter.getCramDateBeginEnd(),"end");
                            predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.cramDateBegin), end));
                        }
                        if (filter.getCramDateEndBegin() != null) {
                            Date begin = DateConvert.convert(filter.getCramDateEndBegin(), "begin");
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateEnd), begin));
                        }
                        if (filter.getCramDateEndEnd() != null) {
                            Date end = DateConvert.convert(filter.getCramDateEndEnd(), "end");
                            predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.cramDateEnd), end));
                        }
                        if (filter.getMajorStartDateBegin() != null) {
                            Date begin = DateConvert.convert(filter.getMajorStartDateBegin(), "begin");
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), begin));
                        }
                        if (filter.getMajorStartDateEnd() != null) {
                            Date end = DateConvert.convert(filter.getMajorStartDateEnd(), "end");
                            predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.majorStartDate), end));
                        }
                        if (filter.getPlanLeaveDateBegin() != null) {
                            Date begin = DateConvert.convert(filter.getPlanLeaveDateBegin(), "begin");
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.planLeaveDate), begin));
                        }
                        if (filter.getPlanLeaveDateEnd() != null) {
                            Date end = DateConvert.convert(filter.getPlanLeaveDateEnd(), "end");
                            predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.planLeaveDate), end));
                        }
                        if(filter.getCurrentUniversity()!=null){
                            predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.currentUniversity),filter.getCurrentUniversity()));
                        }
                        if(filter.getCurrentProvince()!=null){
                            predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.currentProvince),filter.getCurrentProvince()));
                        }
                    }

                }


                return predicate;
            }
        };
    }

    public static Specification<TicketSort> userIs(final User user, final BaseDAO baseDAO) {
        // TODO 实现根据用户所属项目或者所属院校进行查询

        return new Specification<TicketSort>() {
            @Override
            public Predicate toPredicate(Root<TicketSort> ticketSortRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                String userType = user.getUserType();
                String identity = user.getRole().getIdentity();
                String nodeId = user.getNode().getNodeId();
                if("Y0002".equals(identity)){    //基金委用户  Y0002主管
                    Join<TicketSort, Student> student = ticketSortRoot.join(TicketSort_.student);
                    Join<Student, BasicInfo> basicInfo = student.join(Student_.basicInfo);
                    List<Project> projects = user.getProjects();
                    List dispatches = baseDAO.getDispatchesByUserId(user.getUserId());
                    //项目名称
                    if(projects.size() == 1){
                        predicate.getExpressions().add(cb.equal(basicInfo.get(BasicInfo_.projectName), projects.get(0).getProjectId()));
                    }else if(projects.size() >1){
                        Expression eSum = cb.equal(basicInfo.get(BasicInfo_.projectName), projects.get(0).getProjectId());
                        for(int i=1;i<projects.size();i++){
                            Expression e = cb.equal(basicInfo.get(BasicInfo_.projectName),projects.get(i).getProjectId());
                            eSum = cb.or(eSum,e);
                        }
                        predicate.getExpressions().add(eSum);
                    }else{
                        predicate.getExpressions().add(cb.equal(basicInfo.get(BasicInfo_.projectName), "^_^"));
                    }
                    //派遣途径
                    if(dispatches.size() == 1){
                        predicate.getExpressions().add(cb.equal(basicInfo.get(BasicInfo_.dispatch), dispatches.get(0)));
                    }else if(dispatches.size() > 1){
                        Expression dSum = cb.equal(basicInfo.get(BasicInfo_.dispatch), dispatches.get(0));
                        for(int i=1;i<dispatches.size();i++){
                            Expression e = cb.equal(basicInfo.get(BasicInfo_.dispatch),dispatches.get(i));
                            dSum = cb.or(dSum,e);
                        }
                        predicate.getExpressions().add(dSum);
                    }else{
                        predicate.getExpressions().add(cb.equal(basicInfo.get(BasicInfo_.dispatch), "^_^"));
                    }

                }else if("2".equals(userType)){
                    Join<TicketSort, Student> student = ticketSortRoot.join(TicketSort_.student);
                    Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                    predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.currentUniversity), nodeId));
                }
                return predicate;
            }
        };
    }

    public static Specification<TicketSort> stateIs(final String state){
        return new Specification<TicketSort>() {
            @Override
            public Predicate toPredicate(Root<TicketSort> ticketSortRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.like(ticketSortRoot.get(TicketSort_.state), state));
                return predicate;
            }
        };
    }
}
