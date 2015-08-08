package gov.gwssi.csc.scms.service.students;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.abnormal.Abnormal_;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.student.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;


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

                Join<Student, BasicInfo> basicInfo = student.join(Student_.basicInfo);
                ListJoin<Student, Abnormal> abnormals = student.join(Student_.abnormals);
                Join<Student, SchoolRoll> schoolRoll = student.join(Student_.schoolRoll);
                /**学生主表部分*/
                if (filter.getCscId() != null) {
                    predicate.getExpressions().add(cb.like(student.get(Student_.cscId), filter.getCscId()));
                }
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
                /**学籍部分*/
                if (filter.getRegisterState() != null) {
                    predicate.getExpressions().add(cb.like(schoolRoll.get(SchoolRoll_.registerState), filter.getRegisterState()));
                }
//                if (filter.getStudentType() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getAppropriation() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getTeachLanguage() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getSchoolRollState() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getArrivalDateBegin() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getArrivalDateEnd() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getLeaveDateBegin() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getLeaveDateEnd() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getCramDateBeginBegin() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getCramDateBeginEnd() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getCramDateEndBegin() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getCramDateEndEnd() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getMajorStartDateBegin() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getMajorStartDateEnd() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getPlanLeaveDateBegin() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getPlanLeaveDateEnd() != null){predicate.getExpressions().add(cb.like());}
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
                } else {
                    Date end = filter.getArrivalDateEnd();
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(abnormals.get(Abnormal_.applyTime), end));
                }
                /**机票部分*/
//                if (filter.getTicketState() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getInsuranceState() != null){predicate.getExpressions().add(cb.like());}
                /**奖学金部分*/
//                if (filter.getSchReview() != null){predicate.getExpressions().add(cb.like());}
//                if (filter.getSchResult() != null){predicate.getExpressions().add(cb.like());}

                return predicate;
            }
        };
    }
}
