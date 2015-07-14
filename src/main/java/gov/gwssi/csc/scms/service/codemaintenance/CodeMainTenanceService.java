package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.dao.codemaintenance.CodeMainTenanceDAO;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by think on 2015/7/13.
 * 代码维护service层
 */
@Service("codeMainTenanceService")
public class CodeMainTenanceService extends BaseService {
    @Autowired
    private CodeMainTenanceDAO codeMainTenanceDAO;
    public List findAllCode(String tableName,String chinaName){
        return codeMainTenanceDAO.getAllCodeList(tableName,chinaName);
    }

}

