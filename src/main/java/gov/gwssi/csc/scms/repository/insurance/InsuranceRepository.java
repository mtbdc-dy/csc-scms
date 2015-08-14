package gov.gwssi.csc.scms.repository.insurance;

import gov.gwssi.csc.scms.domain.insurance.Insurance;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gc on 2015/7/17.
 * 保险管理接口
 */
public interface InsuranceRepository extends CrudRepository<Insurance,String> {


    Insurance findById(String id);
}
