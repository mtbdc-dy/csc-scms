package gov.gwssi.csc.scms.repository.dictionary;

import gov.gwssi.csc.scms.domain.dictionary.CodeTableClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzishi on 15/7/17.
 */
@Repository("codeTableClassRepository")
public interface CodeTableClassRepository extends CrudRepository<CodeTableClass, String> {
    CodeTableClass findByClassEn(String classEn);
}
