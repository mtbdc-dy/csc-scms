package gov.gwssi.csc.scms.dao.insurance;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * insurance DAO封装类
 */
@Service("insuranceDAO")
public class InsuranceDAO extends BaseDAO {
    public void doSt(String name, List list){
        super.doStatementForRtn(name,list);

    }

}
