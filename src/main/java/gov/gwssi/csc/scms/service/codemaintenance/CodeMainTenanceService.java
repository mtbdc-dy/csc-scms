package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.dao.codemaintenance.CodeMainTenanceDAO;
import gov.gwssi.csc.scms.domain.query.CodeDetailResult;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List findDetailCode(String id,String tableName,String flag){
        return codeMainTenanceDAO.getDetailCodeList(id, tableName, flag);
    }
    @Transactional
    public CodeDetailResult saveCode(CodeDetailResult codeDetailResult){
        return codeMainTenanceDAO.saveCode(codeDetailResult);
    }

}

