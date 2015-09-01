package gov.gwssi.csc.scms.service.timeset;


import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.universities.DimUniv;
import gov.gwssi.csc.scms.domain.universities.DimUniv_;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by LiZhiSheng on 2015/8/31.
 */
public class TimeSetSpecs extends BaseService {
    public static Specification<DimUniv> filterIsLike(final Filter filter, final User user) {
        return new Specification<DimUniv>() {

            @Override
            public Predicate toPredicate(Root<DimUniv> dimUniv, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (filter.getPro()!=null||"null".equals(filter.getPro())) {
                    predicate.getExpressions().add(cb.equal(dimUniv.get(DimUniv_.province), filter.getPro()));
                }
                if (filter.getUniv()!=null||"null".equals(filter.getUniv())) {
                    predicate.getExpressions().add(cb.equal(dimUniv.get(DimUniv_.univId), filter.getUniv()));
                }
                return predicate;
            }
        };
    }
}
