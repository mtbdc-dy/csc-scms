package gov.gwssi.csc.scms.dao.appropriation;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gc on 2015/8/18.
 * scholarshipJ DAO封装类
 */
@Service("appropriationDAO")
public class AppropriationDAO extends BaseDAO {
    public List getListById(String id) {
        List allList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * from scms_stats_appropriation t where 1 = 1");

        allList = super.queryListBySql(stringBuilder.toString());
        return allList;
    }
    public String doSt(String name, List list){
        String id =super.doStatementForRtn(name, list);
        return id;
    }

}
