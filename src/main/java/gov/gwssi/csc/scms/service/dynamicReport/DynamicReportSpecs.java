package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration_;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Wang Zishi on 15/8/31.
 * JPA 查询时所需使用的 Specs
 */
public class DynamicReportSpecs extends BaseService{
    public static Specification<ReportConfiguration> filterIsLike(final Filter filter) {
        return new Specification<ReportConfiguration>() {
            @Override
            public Predicate toPredicate(Root<ReportConfiguration> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                List<Expression<Boolean>> expressions = predicate.getExpressions();

                if (filter.getTitle() != null) {
                    expressions.add(cb.like(root.get(ReportConfiguration_.title), "%" + filter.getTitle() + "%"));
                }

                if (filter.getAccessState() != null){
                    expressions.add(cb.equal(root.get(ReportConfiguration_.accessState), filter.getAccessState()));
                }

                if (filter.getPublishState() != null){
                    expressions.add(cb.equal(root.get(ReportConfiguration_.publishState), filter.getPublishState()));
                }

                if (filter.getCreateTimeFrom() != null && filter.getCreateTimeTo() != null) {
                    Date begin = filter.getCreateTimeFrom();
                    Date end = filter.getCreateTimeTo();
                    predicate.getExpressions().add(cb.between(root.get(ReportConfiguration_.created), begin, end));
                } else if (filter.getArrivalDateBegin() != null) {
                    Date begin = filter.getCreateTimeFrom();
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get(ReportConfiguration_.created), begin));
                } else if (filter.getArrivalDateEnd() != null) {
                    Date end = filter.getCreateTimeTo();
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get(ReportConfiguration_.created), end));
                }

                if (filter.getPublishTimeFrom() != null && filter.getPublishTimeTo() != null) {
                    Date begin = filter.getPublishTimeFrom();
                    Date end = filter.getPublishTimeTo();
                    predicate.getExpressions().add(cb.between(root.get(ReportConfiguration_.time), begin, end));
                } else if (filter.getArrivalDateBegin() != null) {
                    Date begin = filter.getPublishTimeFrom();
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get(ReportConfiguration_.time), begin));
                } else if (filter.getArrivalDateEnd() != null) {
                    Date end = filter.getPublishTimeTo();
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get(ReportConfiguration_.time), end));
                }
                return predicate;
            }
        };
    }
}
