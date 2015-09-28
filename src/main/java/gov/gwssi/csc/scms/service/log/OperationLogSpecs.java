package gov.gwssi.csc.scms.service.log;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.log.OperationLog_;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by LiZhiSheng on 2015/9/6.
 */
public class OperationLogSpecs extends BaseService {
    public static Specification<OperationLog> filterIsLike(final Filter filter, final String userId) {
        return new Specification<OperationLog>() {

            @Override
            public Predicate toPredicate(Root<OperationLog> operationLog, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (filter.getBusinessModule()!=null) {
                    String moduleId = filter.getBusinessModule();
                    predicate.getExpressions().add(cb.equal(operationLog.get(OperationLog_.moduleId), moduleId));
                }
                if (!"".equals(filter.getOptType())) {
                    predicate.getExpressions().add(cb.equal(operationLog.get(OperationLog_.optType), filter.getOptType()));
                }


                if ((filter.getBeginTime() != null&&!"null".equals(filter.getBeginTime())) && (filter.getEndTime() != null&&!"null".equals(filter.getEndTime()))) {
                    Date begin = filter.getBeginTime();
                    Date end = filter.getEndTime();

                    Calendar c = Calendar.getInstance();
                    c.setTime(end);   //设置日期
                    c.add(Calendar.DATE, 1); //日期加1天
                    end = c.getTime();
                    predicate.getExpressions().add(cb.between(operationLog.get(OperationLog_.createD), begin, end));
                } else if (filter.getBeginTime() != null&&!"null".equals(filter.getBeginTime())) {
                    Date begin = filter.getBeginTime();
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(operationLog.get(OperationLog_.createD), begin));
                } else if (filter.getEndTime() != null&&!"null".equals(filter.getEndTime())) {
                    Date end = filter.getEndTime();
                    Calendar c = Calendar.getInstance();
                    c.setTime(end);   //设置日期
                    c.add(Calendar.DATE, 1); //日期加1天
                    end = c.getTime();
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(operationLog.get(OperationLog_.createD), end));
                }
                predicate.getExpressions().add(cb.equal(operationLog.get(OperationLog_.createBy), userId));

                return predicate;
            }
        };
    }
//重载一个 供 学籍变化记录 分页查询
    public static Specification<OperationLog> filterIsLike(final String studentId) {
        return new Specification<OperationLog>() {

            @Override
            public Predicate toPredicate(Root<OperationLog> operationLog, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(!"".equals(studentId)){
                    predicate.getExpressions().add(cb.equal(operationLog.get(OperationLog_.studentId), studentId));

                }
                return predicate;
            }
        };
    }
}
