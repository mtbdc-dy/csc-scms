package gov.gwssi.csc.scms.service.importlog;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.importlog.ImportLog;
import gov.gwssi.csc.scms.domain.importlog.ImportLog_;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/9/1.
 */
public class ImportLogSpecs extends BaseService {
    public static Specification<ImportLog> filterIsLike(final Filter filter, final User user, final String importClass) {
        return new Specification<ImportLog>() {

            @Override
            public Predicate toPredicate(Root<ImportLog> importLog, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if ((filter.getBeginTime() != null&&!"null".equals(filter.getBeginTime())) && (filter.getEndTime() != null&&!"null".equals(filter.getEndTime()))) {
                    Date begin = filter.getBeginTime();
                    Date end = filter.getEndTime();
                    predicate.getExpressions().add(cb.between(importLog.get(ImportLog_.created), begin, end));
                } else if (filter.getBeginTime() != null&&!"null".equals(filter.getBeginTime())) {
                    Date begin = filter.getBeginTime();
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(importLog.get(ImportLog_.created), begin));
                } else if (filter.getEndTime() != null&&!"null".equals(filter.getEndTime())) {
                    Date end = filter.getEndTime();
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(importLog.get(ImportLog_.created), end));
                }
                //1是学籍注册导入 2学历学位导入
                predicate.getExpressions().add(cb.equal(importLog.get(ImportLog_.importclass), importClass));
                //用户权限
                predicate.getExpressions().add(cb.equal(importLog.get(ImportLog_.createBy), user.getFullName()));
                return predicate;
            }
        };
    }
}
