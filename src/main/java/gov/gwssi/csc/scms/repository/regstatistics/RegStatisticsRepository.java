package gov.gwssi.csc.scms.repository.regstatistics;

import gov.gwssi.csc.scms.domain.regstatistics.RegStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Wang Rui on 2015/6/22.
 */
@Repository("regStatisticsRepository")
public interface RegStatisticsRepository extends CrudRepository<RegStatistics, String> {

    List<RegStatistics> findById(String id);

}
