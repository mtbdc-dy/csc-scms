package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodeMainTenance;
import gov.gwssi.csc.scms.domain.codemaintenance.CodeMainTenance_;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionFirst;
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
}
