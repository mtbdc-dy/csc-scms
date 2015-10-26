package gov.gwssi.csc.scms.service.ticket;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.ticket.Ticket_;
import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
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
public class TicketSpecs extends BaseService {
    public static Specification<Ticket> filterIsLike(final Filter filter, final User user) {
        return new Specification<Ticket>() {
            @Override
            public Predicate toPredicate(Root<Ticket> ticket, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                for (Field field : filter.getClass().getDeclaredFields()) {
//                    field.setAccessible(true);
//                    try {
//                        if(field.get(filter) == null){
//                            System.out.println("field.get(filter) = " + field.get(filter));
//                        }
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//                }
//              除了下面的暴力平铺的形式，是否有更好的解决方式？
//              上面是尝试是用反射遍历来实现，但是再处理原模型时遇到了困难
                Predicate predicate = cb.conjunction();


                boolean needBasicInfo = filter.getPassportName() != null
                        || filter.getContinent() != null
                        || filter.getCountry() != null
                        || filter.getProjectAttr() != null
                        || filter.getProjectType() != null
                        || filter.getProjectName() != null
                        || filter.getPlanned() != null
                        || filter.getDispatch() != null
                        || filter.getTravelType() != null
                        || filter.getAnnual() != null;
                boolean needSchoolRoll = filter.getRegisterState() != null
                        || filter.getStudentType() != null
                        || filter.getAppropriation() != null
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
                Date finalDate = null;
                Date intialDate = null;
                int currentYear=0;
                try {
                    Calendar calendar = Calendar.getInstance();
                    currentYear = calendar.get(Calendar.YEAR);
                    SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
                    intialDate = ds.parse(currentYear + "-01-01");
                    finalDate = ds.parse(currentYear + "-12-31");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                predicate.getExpressions().add(cb.greaterThanOrEqualTo(cb.currentDate(),intialDate));
//                predicate.getExpressions().add(cb.lessThan(cb.currentDate(), finalDate));
                predicate.getExpressions().add(cb.between(ticket.get(Ticket_.created), intialDate, finalDate));


                if (filter.getTicketState() != null) {
                    predicate.getExpressions().add(cb.like(ticket.get(Ticket_.state), filter.getTicketState()));
                } else {
                    if ("2".equals(user.getUserType())) {//1 基金委用户 2学校用户
                        predicate.getExpressions().add(cb.in(ticket.get(Ticket_.state)).value("AT0001").value("AT0002").value("AT0005").value("AT0003").value("AT0004"));
                    } else if ("1".equals(user.getUserType())) {
                        predicate.getExpressions().add(cb.in(ticket.get(Ticket_.state)).value("AT0002").value("AT0005").value("AT0003").value("AT0004"));
                    }
                }
                if (filter.getTicketType() != null) {
                    predicate.getExpressions().add(cb.like(ticket.get(Ticket_.type), filter.getTicketType()));

                }
                /**学生主表部分*/
                if (needStudent) {
                    Join<Ticket, Student> student = ticket.join(Ticket_.student);
                    if (filter.getCscId() != null) {
                        predicate.getExpressions().add(cb.like(student.get(Student_.cscId), filter.getCscId()));
                    }
                    if (needBasicInfo) {
                        Join<Student, BasicInfo> basicInfo = student.join(Student_.basicInfo);
                        /**基本信息部分*/
                        if (filter.getPassportName() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.passportName), filter.getPassportName()));
                        }
                        if (filter.getContinent() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.continent), filter.getContinent()));
                        }
                        if (filter.getCountry() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.country), filter.getCountry()));
                        }
                        if (filter.getProjectAttr() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.projectAttr), filter.getProjectAttr()));
                        }
                        if (filter.getProjectType() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.projectType), filter.getProjectType()));
                        }
                        if (filter.getProjectName() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.projectName), filter.getProjectName()));
                        }
                        if (filter.getPlanned() != null) {
                            predicate.getExpressions().add(cb.like(basicInfo.get(BasicInfo_.planned), filter.getPlanned()));
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
                        if (filter.getAppropriation() != null) {
                            predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.appropriation), filter.getAppropriation()));
                        }
                        if (filter.getTeachLanguage() != null) {
                            predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.teachLanguage), filter.getTeachLanguage()));
                        }
                        if (filter.getSchoolRollState() != null) {
                            predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.state), filter.getSchoolRollState()));
                        }
                        if (filter.getArrivalDateBegin() != null && filter.getArrivalDateEnd() != null) {
                            Date begin = filter.getArrivalDateBegin();
                            Date end = filter.getArrivalDateEnd();
                            predicate.getExpressions().add(cb.between(schoolRoll.get(SchoolRoll_.arrivalDate), begin, end));
                        } else if (filter.getArrivalDateBegin() != null) {
                            Date begin = filter.getArrivalDateBegin();
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.arrivalDate), begin));
                        } else if (filter.getArrivalDateEnd() != null) {
                            Date end = filter.getArrivalDateEnd();
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.arrivalDate), end));
                        }
                        if (filter.getLeaveDateBegin() != null && filter.getLeaveDateEnd() != null) {
                            Date begin = filter.getLeaveDateBegin();
                            Date end = filter.getLeaveDateEnd();
                            predicate.getExpressions().add(cb.between(schoolRoll.get(SchoolRoll_.leaveDate), begin, end));
                        } else if (filter.getLeaveDateBegin() != null) {
                            Date begin = filter.getLeaveDateBegin();
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.leaveDate), begin));
                        } else if (filter.getLeaveDateEnd() != null) {
                            Date end = filter.getLeaveDateEnd();
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.leaveDate), end));
                        }
                        if (filter.getCramDateBeginBegin() != null && filter.getCramDateBeginEnd() != null) {
                            Date begin = filter.getCramDateBeginBegin();
                            Date end = filter.getCramDateBeginEnd();
                            predicate.getExpressions().add(cb.between(schoolRoll.get(SchoolRoll_.cramDateBegin), begin, end));
                        } else if (filter.getCramDateBeginBegin() != null) {
                            Date begin = filter.getCramDateBeginBegin();
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateBegin), begin));
                        } else if (filter.getCramDateBeginEnd() != null) {
                            Date end = filter.getCramDateBeginEnd();
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateBegin), end));
                        }
                        if (filter.getCramDateEndBegin() != null && filter.getCramDateEndEnd() != null) {
                            Date begin = filter.getCramDateEndBegin();
                            Date end = filter.getCramDateEndEnd();
                            predicate.getExpressions().add(cb.between(schoolRoll.get(SchoolRoll_.cramDateEnd), begin, end));
                        } else if (filter.getCramDateEndBegin() != null) {
                            Date begin = filter.getCramDateEndBegin();
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateEnd), begin));
                        } else if (filter.getCramDateEndEnd() != null) {
                            Date end = filter.getCramDateEndEnd();
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateEnd), end));
                        }
                        if (filter.getMajorStartDateBegin() != null && filter.getMajorStartDateEnd() != null) {
                            Date begin = filter.getMajorStartDateBegin();
                            Date end = filter.getMajorStartDateEnd();
                            predicate.getExpressions().add(cb.between(schoolRoll.get(SchoolRoll_.majorStartDate), begin, end));
                        } else if (filter.getMajorStartDateBegin() != null) {
                            Date begin = filter.getMajorStartDateBegin();
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), begin));
                        } else if (filter.getMajorStartDateEnd() != null) {
                            Date end = filter.getMajorStartDateEnd();
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), end));
                        }
                        if (filter.getPlanLeaveDateBegin() != null && filter.getPlanLeaveDateEnd() != null) {
                            Date begin = filter.getPlanLeaveDateBegin();
                            Date end = filter.getPlanLeaveDateEnd();
                            predicate.getExpressions().add(cb.between(schoolRoll.get(SchoolRoll_.planLeaveDate), begin, end));
                        } else if (filter.getPlanLeaveDateBegin() != null) {
                            Date begin = filter.getMajorStartDateBegin();
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), begin));
                        } else if (filter.getPlanLeaveDateEnd() != null) {
                            Date end = filter.getMajorStartDateEnd();
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), end));
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

    public static Specification<Ticket> userIs(final User user) {
        // TODO 实现根据用户所属项目或者所属院校进行查询

        return new Specification<Ticket>() {
            @Override
            public Predicate toPredicate(Root<Ticket> ticketRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                String userType = user.getUserType();
                String identity = user.getRole().getIdentity();
                String nodeId = user.getNode().getNodeId();
                if("Y0002".equals(identity)){    //基金委用户  Y0002主管
                    Join<Ticket, Student> student = ticketRoot.join(Ticket_.student);
                    Join<Student, BasicInfo> basicInfo = student.join(Student_.basicInfo);
                    List<Project> projects = user.getProjects();

                    if(projects.size() == 1){
                        predicate.getExpressions().add(cb.equal(basicInfo.get(BasicInfo_.projectName), projects.get(0).getProjectId()));
                    }else if(projects.size() >1){
                        Expression eSum = cb.equal(basicInfo.get(BasicInfo_.projectName), projects.get(0).getProjectId());
                        for(int i=1;i<projects.size();i++){
                            Expression e = cb.equal(basicInfo.get(BasicInfo_.projectName),projects.get(i).getProjectId());
                            eSum = cb.or(eSum,e);
                        }
                        predicate.getExpressions().add(eSum);
                    }

                }else if("2".equals(userType)){
                    Join<Ticket, Student> student = ticketRoot.join(Ticket_.student);
                    Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                    predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.currentUniversity), nodeId));
                }
                return predicate;
            }
        };
    }
}
