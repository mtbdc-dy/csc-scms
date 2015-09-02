package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipJ;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipJ_;
import gov.gwssi.csc.scms.domain.universities.DimUniv_;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;


/**
 * Created by tianj on 2015/9/1.
 */
public class ScholarshipJSpecs extends BaseService {

    public static Specification<ScholarshipJ> filterIsLike(final Filter filter, final User user) {
        return new Specification<ScholarshipJ>() {
            @Override
            public Predicate toPredicate(Root<ScholarshipJ> scholarshipJ, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (filter.getPro()!=null||"null".equals(filter.getPro())) {
                   predicate.getExpressions().add(cb.equal(scholarshipJ.get(ScholarshipJ_.province), filter.getPro()));
                }
                if (filter.getUniv()!=null||"null".equals(filter.getUniv())) {
                    predicate.getExpressions().add(cb.equal(scholarshipJ.get(ScholarshipJ_.univ), filter.getUniv()));
                }
                if(filter.getYear()!=0){
                    predicate.getExpressions().add(cb.equal(scholarshipJ.get(ScholarshipJ_.year), filter.getYear()));
                }
                if(filter.getQualified() != null || "null".equals(filter.getQualified())){
                    predicate.getExpressions().add(cb.like(scholarshipJ.get(ScholarshipJ_.qualified), filter.getQualified()));
                }
                if(filter.getState() != null || "null".equals(filter.getState())){
                    predicate.getExpressions().add(cb.equal(scholarshipJ.get(ScholarshipJ_.state), filter.getState()));
                }

                return predicate;
            }
        };
    }
}
