package gov.gwssi.csc.scms.service.students;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.abnormal.Abnormal_;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.insurance.Insurance_;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX_;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.ticket.Ticket_;
import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.domain.warning.Warning;
import gov.gwssi.csc.scms.utils.DateConvert;
import org.hibernate.jpa.criteria.expression.EntityTypeExpression;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by wang Zishi on 15/8/7.
 * JPA 查询时所需使用的 Specs
 */
public class StudentSpecs {
    public static Specification<Student> cscIdIsLike(final String cscId) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                predicate.getExpressions().add(builder.like(root.get(Student_.cscId), "%" + cscId + "%"));

                return predicate;
            }
        };
    }

    public static Specification<Student> genderIsLike(final String gender) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<Student, BasicInfo> b = root.join(Student_.basicInfo);

                return cb.like(b.get(BasicInfo_.gender), "%" + gender + "%");
            }
        };
    }


    public static Specification<Student> userIs(final User user,final String mode) {
        // TODO 实现根据用户所属项目或者所属院校进行查询

        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                String userType = user.getUserType();
                String identity = user.getRole().getIdentity();
                String nodeId = user.getNode().getNodeId();
                if("Y0002".equals(identity) && !"integratedquery".equals(mode)){    //基金委用户  Y0002主管 并且不是综合查询模块
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
                    }else{
                        predicate.getExpressions().add(cb.equal(basicInfo.get(BasicInfo_.projectName), "^_^"));
                    }

                }else if("2".equals(userType)){
                    Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                    predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.currentUniversity), nodeId));
                }
                return predicate;
            }
        };
    }

    public static Specification<Student> isFreshRegister() {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                predicate.getExpressions().add(cb.notEqual(schoolRoll.get(SchoolRoll_.registed), "AX0002"));
                predicate.getExpressions().add(cb.notEqual(schoolRoll.get(SchoolRoll_.registerState), "AW0001"));
                predicate.getExpressions().add(cb.notEqual(schoolRoll.get(SchoolRoll_.registerState), "AW0004"));

                Date sysDate = null;
                Date finalDate = null;
                Date intialDate = null;
                Date nextIntialDate = null;
                try {
                    Calendar calendar = Calendar.getInstance();
                    int currentYear = calendar.get(Calendar.YEAR);
                    SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
                    intialDate = ds.parse(currentYear + "-01-01");
                    finalDate = ds.parse(currentYear + "-07-01");
                    int nextYear = currentYear + 1;
                    nextIntialDate = ds.parse(nextYear + "-01-01");

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Expression e1 = cb.and(cb.greaterThanOrEqualTo(cb.currentDate(), intialDate),
                        cb.lessThan(cb.currentDate(), finalDate));

                Expression e2 = cb.and(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateBegin), intialDate),
                        cb.lessThan(schoolRoll.get(SchoolRoll_.cramDateBegin), finalDate));


                Expression e3 = cb.and(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), intialDate),
                        cb.lessThan(schoolRoll.get(SchoolRoll_.majorStartDate), finalDate));

                Expression e4 = cb.and(e1,cb.or(e2, e3));

                Expression e5 = cb.and(cb.greaterThanOrEqualTo(cb.currentDate(), finalDate),
                        cb.lessThan(cb.currentDate(), nextIntialDate)
                );
                Expression e6 = cb.and(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateBegin), finalDate),
                        cb.lessThan(schoolRoll.get(SchoolRoll_.cramDateBegin), nextIntialDate)
                );

                Expression e7 = cb.and(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), finalDate),
                        cb.lessThan(schoolRoll.get(SchoolRoll_.majorStartDate), nextIntialDate)
                );

                Expression e8 = cb.and(e5, cb.or(e6, e7));

                Expression e9 = cb.or(e4,e8);

                predicate.getExpressions().add(e9);
                return predicate;
            }
        };
    }

    public static Specification<Student> isOldRegister() {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.registed), "AX0002"));
                predicate.getExpressions().add(cb.notEqual(schoolRoll.get(SchoolRoll_.leaveChina), "BA0002"));

                Date sysDate = null;
                Date finalDate = null;
                Date intialDate = null;
                Date nextIntialDate = null;
                int currentYear=0;
                try {
                    Calendar calendar = Calendar.getInstance();
                    currentYear = calendar.get(Calendar.YEAR);
                    SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
                    intialDate = ds.parse(currentYear + "-01-01");
                    finalDate = ds.parse(currentYear + "-07-01");
                    int nextYear = currentYear + 1;
                    nextIntialDate = ds.parse(nextYear + "-01-01");

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                predicate.getExpressions().add(cb.notEqual(schoolRoll.get(SchoolRoll_.registerYear), currentYear));

                Expression e1 = cb.greaterThan(schoolRoll.get(SchoolRoll_.cramDateEnd), nextIntialDate);
                Expression e2 = cb.lessThan(schoolRoll.get(SchoolRoll_.majorStartDate), nextIntialDate);
                Expression e3 = cb.or(e1,e2);
                predicate.getExpressions().add(e3);
                return predicate;
            }
        };
    }

    public static Specification<Student> isSchoolStudent() {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.registed), "AX0002"));
                predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.leaveChina), "BA0001"));
                return predicate;
            }
        };
    }

    public static Specification<Student> isLeaveStudent() {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.registed), "AX0002"));
                predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.leaveChina), "BA0002"));
                return predicate;
            }
        };
    }

    public static Specification<Student> isLeaveChina() {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.registed), "AX0002"));
                predicate.getExpressions().add(cb.notEqual(schoolRoll.get(SchoolRoll_.leaveChina), "BA0002"));
                return predicate;
            }
        };
    }

    public static Specification<Student> filterIsLike(final Filter filter) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                        || filter.getCurrentProvince() != null
                        || filter.getCurrentUniversity() != null;
                boolean needAbnormals = filter.getAbnormalState() != null
                        || filter.getAbnormalDateBegin() != null
                        || filter.getAbnormalDateEnd() != null;
                boolean needTickets = filter.getTicketState() != null;
                boolean needInsurances = filter.getPreSta()!= null;
                boolean needScholarshipXs = filter.getSchReview()!= null
                        ||filter.getSchResult()!= null;

                /**学生主表部分*/
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
                    if (filter.getArrivalDateBegin() != null) {
                        Date begin = filter.getArrivalDateBegin();
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.arrivalDate), begin));
                    }
                    if (filter.getArrivalDateEnd() != null) {
                        Date end = filter.getArrivalDateEnd();
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.arrivalDate), end));
                    }
                    if (filter.getLeaveDateBegin() != null) {
                        Date begin = filter.getLeaveDateBegin();
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.leaveDate), begin));
                    }
                    if (filter.getLeaveDateEnd() != null) {
                        Date end = filter.getLeaveDateEnd();
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.leaveDate), end));
                    }
                    if (filter.getCramDateBeginBegin() != null) {
                        Date begin = filter.getCramDateBeginBegin();
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateBegin), begin));
                    }
                    if (filter.getCramDateBeginEnd() != null) {
                        Date end = filter.getCramDateBeginEnd();
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateBegin), end));
                    }
                    if (filter.getCramDateEndBegin() != null) {
                        Date begin = filter.getCramDateEndBegin();
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateEnd), begin));
                    }
                    if (filter.getCramDateEndEnd() != null) {
                        Date end = filter.getCramDateEndEnd();
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateEnd), end));
                    }
                    if (filter.getMajorStartDateBegin() != null) {
                        Date begin = filter.getMajorStartDateBegin();
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), begin));
                    }
                    if (filter.getMajorStartDateEnd() != null) {
                        Date end = filter.getMajorStartDateEnd();
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), end));
                    }
                    if (filter.getPlanLeaveDateBegin() != null) {
                        Date begin = filter.getMajorStartDateBegin();
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), begin));
                    }
                    if (filter.getPlanLeaveDateEnd() != null) {
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
                if (needAbnormals) {
                    ListJoin<Student, Abnormal> abnormals = student.join(Student_.abnormals);
                    /**异动部分*/
                    if (filter.getAbnormalState() != null) {
                        predicate.getExpressions().add(cb.like(abnormals.get(Abnormal_.state), filter.getAbnormalState()));
                    }
                    if (filter.getAbnormalDateBegin() != null && filter.getAbnormalDateEnd() != null) {
                        Date begin = filter.getAbnormalDateBegin();
                        Date end = filter.getArrivalDateEnd();
                        predicate.getExpressions().add(cb.between(abnormals.get(Abnormal_.applyTime), begin, end));
                    } else if (filter.getAbnormalDateBegin() != null) {
                        Date begin = filter.getAbnormalDateBegin();
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(abnormals.get(Abnormal_.applyTime), begin));
                    } else if (filter.getAbnormalDateEnd() != null) {
                        Date end = filter.getArrivalDateEnd();
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(abnormals.get(Abnormal_.applyTime), end));
                    }
                }
                if (needTickets) {
                    ListJoin<Student, Ticket> tickets = student.join(Student_.tickets);
                    /**机票部分*/
                    if (filter.getTicketState() != null) {
                        predicate.getExpressions().add(cb.like(tickets.get(Ticket_.state), filter.getTicketState()));
                    }
                }

                /**保险部分*/
                if (needInsurances) {
                    ListJoin<Student, Insurance> insurances = student.join(Student_.insurances);
                    if (filter.getPreSta() != null) {
                        predicate.getExpressions().add(cb.like(insurances.get(Insurance_.preSta), filter.getPreSta()));
                    }
                }

                /**奖学金部分*/
                if (needScholarshipXs) {
                    ListJoin<Student, ScholarshipX> scholarshipXs = student.join(Student_.scholarshipXs);
                    if (filter.getSchReview() != null) {
                        predicate.getExpressions().add(cb.like(scholarshipXs.get(ScholarshipX_.schReview), filter.getSchReview()));
                    }
                    if (filter.getSchResult() != null) {
                        predicate.getExpressions().add(cb.like(scholarshipXs.get(ScholarshipX_.schResult), filter.getSchResult()));
                    }
                }

                return predicate;
            }
        };
    }

    public static Specification<Student> filterIsLike(final Filter filter,final String mode) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> student, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                        || filter.getCurrentProvince() !=null
                        || filter.getCurrentUniversity() !=null;

                /**学生主表部分*/
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
                    if (filter.getArrivalDateBegin() != null) {
                        Date begin = DateConvert.convert(filter.getArrivalDateBegin(),"begin");
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
                        Date begin = DateConvert.convert(filter.getCramDateEndBegin(),"begin");
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.cramDateEnd), begin));
                    }
                    if (filter.getCramDateEndEnd() != null) {
                        Date end = DateConvert.convert(filter.getCramDateEndEnd(),"end");
                        predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.cramDateEnd), end));
                    }
                    if (filter.getMajorStartDateBegin() != null) {
                        Date begin = DateConvert.convert(filter.getMajorStartDateBegin(),"begin");
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), begin));
                    }
                    if (filter.getMajorStartDateEnd() != null) {
                        Date end = DateConvert.convert(filter.getMajorStartDateEnd(),"end");
                        predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.majorStartDate), end));
                    }
                    if (filter.getPlanLeaveDateBegin() != null) {
                        Date begin = DateConvert.convert(filter.getMajorStartDateBegin(),"begin");
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(schoolRoll.get(SchoolRoll_.majorStartDate), begin));
                    }
                    if (filter.getPlanLeaveDateEnd() != null) {
                        Date end = DateConvert.convert(filter.getMajorStartDateEnd(),"end");
                        predicate.getExpressions().add(cb.lessThan(schoolRoll.get(SchoolRoll_.majorStartDate), end));
                    }
                    if(filter.getCurrentUniversity()!=null){
                        predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.currentUniversity),filter.getCurrentUniversity()));
                    }
                    if(filter.getCurrentProvince()!=null){
                        predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.currentProvince),filter.getCurrentProvince()));
                    }
                }
                if("warning".equals(mode)){
                    Join<Student, Warning> warning = student.join(Student_.warning);
                }
                return predicate;
            }
        };
    }


}
