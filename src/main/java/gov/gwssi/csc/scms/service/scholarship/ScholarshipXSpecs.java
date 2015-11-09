package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX_;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tianj on 2015/9/1.
 */
public class ScholarshipXSpecs extends BaseService {

    public static Specification<ScholarshipX> filterIsLike(final Filter filter, final User user, final String school) {
        return new Specification<ScholarshipX>() {
            @Override
            public Predicate toPredicate(Root<ScholarshipX> scholarshipX, CriteriaQuery<?> query, CriteriaBuilder cb) {
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

                /**奖学金部分*/
                if (filter.getSchReview() != null) {
                    predicate.getExpressions().add(cb.like(scholarshipX.get(ScholarshipX_.schReview), filter.getSchReview()));
                }
                if(filter.getYear() != 0){
                    predicate.getExpressions().add(cb.equal(scholarshipX.get(ScholarshipX_.year), filter.getYear()));
                } else{
                    Calendar calendar = Calendar.getInstance();
                    int currentYear = calendar.get(Calendar.YEAR);
                    predicate.getExpressions().add(cb.equal(scholarshipX.get(ScholarshipX_.year), currentYear));
                }
                if (filter.getSchResult() != null) {
                    predicate.getExpressions().add(cb.like(scholarshipX.get(ScholarshipX_.schResult), filter.getSchResult()));
                }
                if(filter.getCscResult() != null){
                    if("1".equals(user.getUserType())){
                        predicate.getExpressions().add(cb.like(scholarshipX.get(ScholarshipX_.cscResult), filter.getCscResult()));
                    }else if("2".equals(user.getUserType())){
                        predicate.getExpressions().add(cb.like(scholarshipX.get(ScholarshipX_.schResult), filter.getCscResult()));
                    }
                }
                predicate.getExpressions().add(cb.like(scholarshipX.get(ScholarshipX_.school), school));

                /**学生主表部分*/
                if (needStudent) {
                    Join<ScholarshipX, Student> student = scholarshipX.join(ScholarshipX_.student);
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

    public static Specification<ScholarshipX> userIs(final User user) {
        // TODO 实现根据用户所属项目或者所属院校进行查询

        return new Specification<ScholarshipX>() {
            @Override
            public Predicate toPredicate(Root<ScholarshipX> scholarshipXRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                String userType = user.getUserType();
                String identity = user.getRole().getIdentity();
                String nodeId = user.getNode().getNodeId();
                if("Y0002".equals(identity)){    //基金委用户  Y0002主管
                    Join<ScholarshipX, Student> student = scholarshipXRoot.join(ScholarshipX_.student);
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
                    Join<ScholarshipX, Student> student = scholarshipXRoot.join(ScholarshipX_.student);
                    Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                    predicate.getExpressions().add(cb.equal(schoolRoll.get(SchoolRoll_.currentUniversity), nodeId));
                }
                return predicate;
            }
        };
    }
}
