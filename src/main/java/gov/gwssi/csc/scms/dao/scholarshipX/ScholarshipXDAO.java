package gov.gwssi.csc.scms.dao.scholarshipX;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * scholarshipX DAO封装类
 */
@Service("scholarshipXDAO")
public class ScholarshipXDAO extends BaseDAO {
    public void doSt(String name, List list){
        super.doStatementForRtn(name,list);

    }

}
