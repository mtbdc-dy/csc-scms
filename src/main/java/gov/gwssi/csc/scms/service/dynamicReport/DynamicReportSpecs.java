package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration_;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Wang Zishi on 15/8/31.
 * JPA 查询时所需使用的 Specs
 */
public class DynamicReportSpecs extends BaseService{
    public static Specification<Configuration> filterIsLike(final Filter filter) {
        return new Specification<Configuration>() {
            @Override
            public Predicate toPredicate(Root<Configuration> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                List<Expression<Boolean>> expressions = predicate.getExpressions();

                if (filter.getTitle() != null) {
                    expressions.add(cb.like(root.get(Configuration_.title), "%" + filter.getTitle() + "%"));
                }

                if (filter.getAccessState() != null){
                    expressions.add(cb.equal(root.get(Configuration_.accessState), filter.getAccessState()));
                }

                if (filter.getMode() != null){
                    expressions.add(cb.equal(root.get(Configuration_.reportType), filter.getMode()));
                }
//                if (filter.getPublishState() != null){
//                    expressions.add(cb.equal(root.get(Configuration_.publishState), filter.getPublishState()));
//                }
                Calendar begin = Calendar.getInstance(Locale.CHINA);
                Calendar end = Calendar.getInstance(Locale.CHINA);
                if (filter.getCreateTimeFrom() != null && filter.getCreateTimeTo() != null) {
                    begin.setTime(filter.getCreateTimeFrom());
                    end.setTime(filter.getCreateTimeTo());
                    predicate.getExpressions().add(cb.between(root.get(Configuration_.created), begin, end));
                } else if (filter.getCreateTimeFrom() != null) {
                    begin.setTime(filter.getCreateTimeFrom());
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get(Configuration_.created), begin));
                } else if (filter.getCreateTimeTo() != null) {
                    end.setTime(filter.getCreateTimeTo());
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get(Configuration_.created), end));
                }

//                if (filter.getPublishTimeFrom() != null && filter.getPublishTimeTo() != null) {
//                    Date begin = filter.getPublishTimeFrom();
//                    Date end = filter.getPublishTimeTo();
//                    predicate.getExpressions().add(cb.between(root.get(Configuration_.time), begin, end));
//                } else if (filter.getArrivalDateBegin() != null) {
//                    Date begin = filter.getPublishTimeFrom();
//                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get(Configuration_.time), begin));
//                } else if (filter.getArrivalDateEnd() != null) {
//                    Date end = filter.getPublishTimeTo();
//                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get(Configuration_.time), end));
//                }
                return predicate;
            }
        };
    }
}
