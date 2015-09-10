package gov.gwssi.csc.scms.service.regstatistics;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.regstatistics.RegStatistics;
import gov.gwssi.csc.scms.domain.regstatistics.RegStatistics_;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LiZhiSheng on 2015/9/2.
 */
public class RegStatisticsSpecs extends BaseService {

   public static Specification<RegStatistics> filterIsLike(final String sameId) {
        return new Specification<RegStatistics>(){

            @Override
            public Predicate toPredicate(Root<RegStatistics> regStatistics, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(!"".equals(sameId)) {
                    predicate.getExpressions().add(cb.equal(regStatistics.get(RegStatistics_.sameId), sameId));
                }
                return predicate;
            }

        };
    }
}
