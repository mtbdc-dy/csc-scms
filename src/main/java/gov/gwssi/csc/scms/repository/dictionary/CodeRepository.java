package gov.gwssi.csc.scms.repository.dictionary;

import gov.gwssi.csc.scms.domain.dictionary.Code;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 代码单元仓库
 * Created by wangzishi on 15/10/29.
 */
@Repository
public interface CodeRepository extends CrudRepository<Code, String> {
}
