package gov.gwssi.csc.scms.service.appropriation;

import gov.gwssi.csc.scms.domain.appropriation.Appropriation;
import gov.gwssi.csc.scms.domain.appropriation.Appropriation_;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by LiZhiSheng on 2015/9/14.
 */
public class AppropriationSpecs extends BaseService {
    public static Specification<Appropriation> filterIsLike(final String sameId) {
        return new Specification<Appropriation>(){

            @Override
            public Predicate toPredicate(Root<Appropriation> appropriation, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(!"".equals(sameId)) {
                    predicate.getExpressions().add(cb.equal(appropriation.get(Appropriation_.sameId), sameId));
                }
                return predicate;
            }

        };
    }
}
