package gov.gwssi.csc.scms.service.log;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.log.OperationLog_;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.utils.DateConvert;
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
                if (filter.getBeginTime() != null&&!"null".equals(filter.getBeginTime())) {
                    Date begin = DateConvert.convert(filter.getBeginTime(),"begin");
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(operationLog.get(OperationLog_.createD), begin));
                }
                if (filter.getEndTime() != null&&!"null".equals(filter.getEndTime())) {
                    Date end = DateConvert.convert(filter.getEndTime(),"end");
                    predicate.getExpressions().add(cb.lessThan(operationLog.get(OperationLog_.createD), end));
                }
                if(filter.getCscId() != null){
                    predicate.getExpressions().add(cb.equal(operationLog.get(OperationLog_.cscId), filter.getCscId()));
                }
                if(filter.getPassportName() != null){
                    predicate.getExpressions().add(cb.equal(operationLog.get(OperationLog_.passportName), filter.getPassportName()));
                }
                if(filter.getCreateBy() != null){
                    predicate.getExpressions().add(cb.equal(operationLog.get(OperationLog_.createBy), filter.getCreateBy()));
                }
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
