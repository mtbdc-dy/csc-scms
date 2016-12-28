package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.*;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by LiZhiSheng on 2015/9/10.
 */
public class CodeMainTenanceSpecs extends BaseService {
    public static Specification<CodeMainTenance> filterIsLike(final Filter filter, final User user) {
        return new Specification<CodeMainTenance>() {

            @Override
            public Predicate toPredicate(Root<CodeMainTenance> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (filter.getChinaName()!=null) {
                    String chinaName = "";
                    try {
                         chinaName = URLDecoder.decode(filter.getChinaName(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    predicate.getExpressions().add(cb.like(codeMainTenance.get(CodeMainTenance_.tableCh).as(String.class), "%"+chinaName+"%"));
                }
                if (filter.getTableName()!=null) {
                    predicate.getExpressions().add(cb.like(codeMainTenance.get(CodeMainTenance_.tableEn).as(String.class), "%"+filter.getTableName()+"%"));
                }
                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceRegionFirst> filterIsLike() {
        return new Specification<CodemaintanenceRegionFirst>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceRegionFirst> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceRegionSecond> filterIsLikeRegionSecond() {
        return new Specification<CodemaintanenceRegionSecond>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceRegionSecond> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceRegionThird> filterIsLikeRegionThird() {
        return new Specification<CodemaintanenceRegionThird>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceRegionThird> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceUniv> filterIsLikeUniv() {
        return new Specification<CodemaintanenceUniv>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceUniv> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceProjectFirst> filterIsLikeProjectFirst() {
        return new Specification<CodemaintanenceProjectFirst>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceProjectFirst> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceProjectSecond> filterIsLikeProjectSecond() {
        return new Specification<CodemaintanenceProjectSecond>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceProjectSecond> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceProjectThird> filterIsLikeProjectThird() {
        return new Specification<CodemaintanenceProjectThird>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceProjectThird> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceAnmlFirst> filterIsLikeAnmlFirst() {
        return new Specification<CodemaintanenceAnmlFirst>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceAnmlFirst> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceAnmlSecond> filterIsLikeAnmlSecond() {
        return new Specification<CodemaintanenceAnmlSecond>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceAnmlSecond> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceSubjectFirst> filterIsLikeSubjectFirst() {
        return new Specification<CodemaintanenceSubjectFirst>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceSubjectFirst> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceSubjectSecond> filterIsLikeSubjectSecond() {
        return new Specification<CodemaintanenceSubjectSecond>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceSubjectSecond> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceSubjectThird> filterIsLikeSubjectThird() {
        return new Specification<CodemaintanenceSubjectThird>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceSubjectThird> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceDeptFirst> filterIsLikeDeptFirst() {
        return new Specification<CodemaintanenceDeptFirst>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceDeptFirst> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceDeptSecond> filterIsLikeDeptSecond() {
        return new Specification<CodemaintanenceDeptSecond>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceDeptSecond> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }
    public static Specification<CodemaintanenceTranslate> filterIsLikeTranslate(final String classId) {
        return new Specification<CodemaintanenceTranslate>() {

            @Override
            public Predicate toPredicate(Root<CodemaintanenceTranslate> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                if (!"".equals(classId)) {
                    predicate.getExpressions().add(cb.equal(codeMainTenance.get(CodemaintanenceTranslate_.classid).as(String.class),classId));
                }
                return predicate;
            }
        };
    }
    public static Specification<CodemaintenanceAgency> filterIsLikeAgency() {
        return new Specification<CodemaintenanceAgency>() {

            @Override
            public Predicate toPredicate(Root<CodemaintenanceAgency> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                return predicate;
            }
        };
    }

}
