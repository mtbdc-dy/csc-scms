package gov.gwssi.csc.scms.repository.dynamicReport;


import gov.gwssi.csc.scms.domain.dynamicReport.DTableList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 所有已选择表清单操作接口
 */
@Repository("dTableListRepository")
public interface DTableListRepository extends CrudRepository<DTableList,String> {
    List<DTableList> findByConfigId(String configId);
}
