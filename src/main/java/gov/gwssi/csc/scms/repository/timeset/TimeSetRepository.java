package gov.gwssi.csc.scms.repository.timeset;

import gov.gwssi.csc.scms.domain.universities.DimUniv;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LiZhiSheng on 2015/8/31.
 */
public interface TimeSetRepository extends CrudRepository<DimUniv,String>, JpaSpecificationExecutor<DimUniv> {


}
