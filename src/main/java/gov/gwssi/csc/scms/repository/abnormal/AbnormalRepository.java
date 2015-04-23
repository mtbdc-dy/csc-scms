package gov.gwssi.csc.scms.repository.abnormal;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lzs on 2015/4/23.
 * 异动申请接口
 */
@Repository("abnormalRepository")
public interface AbnormalRepository extends CrudRepository<Abnormal,Long> {


}
