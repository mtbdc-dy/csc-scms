package gov.gwssi.csc.scms.repository.insurance;

import gov.gwssi.csc.scms.domain.insurance.Insurance;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * 保险管理接口
 */
public interface InsuranceRepository extends CrudRepository<Insurance,String> , JpaSpecificationExecutor<Insurance> {


    Insurance findById(String id);
    List<Insurance> findByStudentIdOrderByYearDesc(String studentId);
    List<Insurance> findByPreSta(String preSta);
    List<Insurance> findByInsurSta(String insurSta);
    Insurance findByStudentIdAndInsurSta(String studentId,String insurSta);
}
