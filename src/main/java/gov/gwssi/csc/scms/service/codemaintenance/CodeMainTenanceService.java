package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.dao.codemaintenance.CodeMainTenanceDAO;
import gov.gwssi.csc.scms.domain.codemaintenance.CodeMainTenance;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionFirst;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.query.CodeDetailResult;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.codemaintenance.CodeMainTenanceRegion1Repository;
import gov.gwssi.csc.scms.repository.codemaintenance.CodeMainTenanceRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by think on 2015/7/13.
 * 代码维护service层
 */
@Service("codeMainTenanceService")
public class CodeMainTenanceService extends CodeMainTenanceSpecs {
    @Autowired
    private CodeMainTenanceDAO codeMainTenanceDAO;
    @Autowired
    @Qualifier("codeMainTenanceRepository")
    private CodeMainTenanceRepository codeMainTenanceRepository;
    @Autowired
    @Qualifier("codeMainTenanceRegion1Repository")
    private CodeMainTenanceRegion1Repository codeMainTenanceRegion1Repository;
    public List findAllCode(String tableName,String chinaName){
        return codeMainTenanceDAO.getAllCodeList(tableName,chinaName);
    }
    public List findDetailCode(String id,String tableName,String flag){
        return codeMainTenanceDAO.getDetailCodeList(id, tableName, flag);
    }
    public List getParentCode(String type){
        return codeMainTenanceDAO.getParentCode(type);
    }
    @Transactional
    public CodeDetailResult saveCode(CodeDetailResult codeDetailResult){
        return codeMainTenanceDAO.saveCode(codeDetailResult);
    }
    @Transactional
    public String saveNewCode(CodeDetailResult codeDetailResult,String type){
        return codeMainTenanceDAO.saveNewCode(codeDetailResult, type);
    }
    public CodeDetailResult selectCode(CodeDetailResult codeDetailResult,String zdz){
        return codeMainTenanceDAO.selectCode(codeDetailResult, zdz);
    }
    public CodeDetailResult selectCode(CodeDetailResult codeDetailResult){
        return codeMainTenanceDAO.selectCode(codeDetailResult);
    }

    public Page<CodeMainTenance> getCodeMainTenancesPagingByFilter(Filter filter,Integer page,Integer size,User user) {
        Specification<CodeMainTenance> specA = filterIsLike(filter,user);
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceRepository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "seq"));
    }
    public Page<CodemaintanenceRegionFirst> getCodemaintanenceRegionFirstsPagingByFilter(Integer page,Integer size) {
        Specification<CodemaintanenceRegionFirst> specA = filterIsLike();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceRegion1Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }
}

