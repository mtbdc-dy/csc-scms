package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodeMainTenance;
import gov.gwssi.csc.scms.domain.codemaintenance.CodeMainTenance_;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by LiZhiSheng on 2015/9/10.
 */
public class CodeMainTenanceSpecs extends BaseService {
    public static Specification<CodeMainTenance> filterIsLike(final Filter filter, final User user) {
        return new Specification<CodeMainTenance>() {

            @Override
            public Predicate toPredicate(Root<CodeMainTenance> codeMainTenance, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (!"".equals(filter.getChinaName())) {
                    predicate.getExpressions().add(cb.equal(codeMainTenance.get(CodeMainTenance_.tableCh), filter.getChinaName()));
                }
                if (!"".equals(filter.getTableName())) {
                    predicate.getExpressions().add(cb.equal(codeMainTenance.get(CodeMainTenance_.tableEn), filter.getTableName()));
                }
                return predicate;
            }
        };
    }
}
