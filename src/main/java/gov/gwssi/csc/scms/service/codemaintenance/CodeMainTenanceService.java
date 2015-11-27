package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.dao.codemaintenance.CodeMainTenanceDAO;
import gov.gwssi.csc.scms.domain.codemaintenance.*;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.query.CodeDetailResult;
import gov.gwssi.csc.scms.domain.user.PwdToken;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.codemaintenance.*;
import gov.gwssi.csc.scms.repository.user.UserRepository;
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
public class CodeMainTenanceService extends CodeMainTenanceSpecs
{


    @Autowired
    private CodeMainTenanceDAO codeMainTenanceDAO;
    @Autowired
    @Qualifier("codeMainTenanceRepository")
    private CodeMainTenanceRepository codeMainTenanceRepository;
    @Autowired
    @Qualifier("codeMainTenanceRegion1Repository")
    private CodeMainTenanceRegion1Repository codeMainTenanceRegion1Repository;
    @Autowired
    @Qualifier("codeMainTenanceRegion2Repository")
    private CodeMainTenanceRegion2Repository codeMainTenanceRegion2Repository;
    @Autowired
    @Qualifier("codeMainTenanceRegion3Repository")
    private CodeMainTenanceRegion3Repository codeMainTenanceRegion3Repository;
    @Autowired
    @Qualifier("codeMainTenanceUnivRepository")
    private CodeMainTenanceUnivRepository codeMainTenanceUnivRepository;
    @Autowired
    @Qualifier("codeMainTenanceProject1Repository")
    private CodeMainTenanceProject1Repository codeMainTenanceProject1Repository;
    @Autowired
    @Qualifier("codeMainTenanceProject2Repository")
    private CodeMainTenanceProject2Repository codeMainTenanceProject2Repository;
    @Autowired
    @Qualifier("codeMainTenanceProject3Repository")
    private CodeMainTenanceProject3Repository codeMainTenanceProject3Repository;
    @Autowired
    @Qualifier("codeMainTenanceAnml1Repository")
    private CodeMainTenanceAnml1Repository codeMainTenanceAnml1Repository;
    @Autowired
    @Qualifier("codeMainTenanceAnml2Repository")
    private CodeMainTenanceAnml2Repository codeMainTenanceAnml2Repository;
    @Autowired
    @Qualifier("codeMainTenanceSubject1Repository")
    private CodeMainTenanceSubject1Repository codeMainTenanceSubject1Repository;
    @Autowired
    @Qualifier("codeMainTenanceSubject2Repository")
    private CodeMainTenanceSubject2Repository codeMainTenanceSubject2Repository;
    @Autowired
    @Qualifier("codeMainTenanceSubject3Repository")
    private CodeMainTenanceSubject3Repository  codeMainTenanceSubject3Repository;
    @Autowired
    @Qualifier("codeMainTenanceDept1Repository")
    private CodeMainTenanceDept1Repository     codeMainTenanceDept1Repository;
    @Autowired
    @Qualifier("codeMainTenanceDept2Repository")
    private CodeMainTenanceDept2Repository     codeMainTenanceDept2Repository;
    @Autowired
    @Qualifier("codeMainTenanceTranslateRepository")
    private CodeMainTenanceTranslateRepository codeMainTenanceTranslateRepository;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository                     userRepository;

    public String changeUniversitySortValue(String id, String direction, Long step)
    {
        String result = codeMainTenanceUnivRepository.changeSortValue(id, direction, step);
        System.out.println("result = " + result);
        return result;
    }

    public List findAllCode(String tableName, String chinaName)
    {
        return codeMainTenanceDAO.getAllCodeList(tableName, chinaName);
    }

    public List findDetailCode(String id, String tableName, String flag)
    {
        return codeMainTenanceDAO.getDetailCodeList(id, tableName, flag);
    }

    public List getParentCode(String type)
    {
        return codeMainTenanceDAO.getParentCode(type);
    }

    @Transactional
    public CodeDetailResult saveCode(CodeDetailResult codeDetailResult)
    {
        return codeMainTenanceDAO.saveCode(codeDetailResult);
    }

    @Transactional
    public String saveNewCode(CodeDetailResult codeDetailResult, String type)
    {
        return codeMainTenanceDAO.saveNewCode(codeDetailResult, type);
    }

    public CodeDetailResult selectCode(CodeDetailResult codeDetailResult, String zdz)
    {
        return codeMainTenanceDAO.selectCode(codeDetailResult, zdz);
    }

    @Transactional
    public CodeDetailResult selectCode(CodeDetailResult codeDetailResult)
    {
        CodeDetailResult result = codeMainTenanceDAO.selectCode(codeDetailResult);
        PwdToken user = userRepository.getPwdToken(result.getFULLNAME());
        result.setFULLNAME(user.getFullName());
        return result;
    }

    public Page<CodeMainTenance> getCodeMainTenancesPagingByFilter(Filter filter, Integer page, Integer size, User user)
    {
        Specification<CodeMainTenance> specA = filterIsLike(filter, user);
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceRepository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "seq"));
    }

    public Page<CodemaintanenceRegionFirst> getCodemaintanenceRegionFirstsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceRegionFirst> specA = filterIsLike();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceRegion1Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceRegionSecond> getCodemaintanenceRegionSecondsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceRegionSecond> specA = filterIsLikeRegionSecond();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceRegion2Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceRegionThird> getCodemaintanenceRegionThirdsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceRegionThird> specA = filterIsLikeRegionThird();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceRegion3Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceUniv> getCodemaintanenceUnivsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceUniv> specA = filterIsLikeUniv();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceUnivRepository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "customSort"));
    }

    public Page<CodemaintanenceProjectFirst> getCodemaintanenceProjectFirstsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceProjectFirst> specA = filterIsLikeProjectFirst();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceProject1Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceProjectSecond> getCodemaintanenceProjectSecondsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceProjectSecond> specA = filterIsLikeProjectSecond();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceProject2Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceProjectThird> getCodemaintanenceProjectThirdsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceProjectThird> specA = filterIsLikeProjectThird();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceProject3Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceAnmlFirst> getCodemaintanenceAnmlFirstsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceAnmlFirst> specA = filterIsLikeAnmlFirst();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceAnml1Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceAnmlSecond> getCodemaintanenceAnmlSecondsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceAnmlSecond> specA = filterIsLikeAnmlSecond();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceAnml2Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceSubjectFirst> getCodemaintanenceSubjectFirstsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceSubjectFirst> specA = filterIsLikeSubjectFirst();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceSubject1Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceSubjectSecond> getCodemaintanenceSubjectSecondsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceSubjectSecond> specA = filterIsLikeSubjectSecond();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceSubject2Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceSubjectThird> getCodemaintanenceSubjectThirdsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceSubjectThird> specA = filterIsLikeSubjectThird();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceSubject3Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceDeptFirst> getCodemaintanenceDeptFirstsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceDeptFirst> specA = filterIsLikeDeptFirst();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceDept1Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceDeptSecond> getCodemaintanenceDeptSecondsPagingByFilter(Integer page, Integer size)
    {
        Specification<CodemaintanenceDeptSecond> specA = filterIsLikeDeptSecond();
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceDept2Repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }

    public Page<CodemaintanenceTranslate> getCodemaintanenceTranslatesPagingByFilter(Integer page, Integer size, String classid)
    {
        Specification<CodemaintanenceTranslate> specA = filterIsLikeTranslate(classid);
//        Specification<Ticket> specB = userIs(user);
        return codeMainTenanceTranslateRepository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }
}

