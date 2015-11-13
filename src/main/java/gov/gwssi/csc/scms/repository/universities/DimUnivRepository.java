package gov.gwssi.csc.scms.repository.universities;

import gov.gwssi.csc.scms.domain.universities.DimUniv;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tianj on 2015/11/9.
 */
@Repository
public interface DimUnivRepository extends CrudRepository<DimUniv, String> {
}
