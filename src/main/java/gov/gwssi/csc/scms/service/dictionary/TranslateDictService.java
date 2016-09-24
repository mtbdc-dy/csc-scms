package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.CodeTableDAO;
import gov.gwssi.csc.scms.domain.dictionary.Code;
import gov.gwssi.csc.scms.domain.dictionary.CodeTableClass;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.repository.dictionary.CodeRepository;
import gov.gwssi.csc.scms.repository.dictionary.CodeTableClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by WangZhenghua on 2015/4/27.
 * Modified by Wang Zishi on 2015/7/21.
 * 转义代码表(一层)Service类
 */

@Service("translateDictService")
public class TranslateDictService {

    @Autowired
    private CodeTableDAO codeTableDAO;



    @Autowired
    @Qualifier("codeTableClassRepository")
    private CodeTableClassRepository codeTableClassRepository;

    @Qualifier("codeRepository")
    @Autowired
    private CodeRepository codeRepository;

    public Code getCode(String id){
        return codeRepository.findOne(id);
    }

    public Map<String, String> getAllCode(){
        Iterable<Code> all = codeRepository.findAll();
        Map<String, String> codes = new HashMap<String, String>();
        for (Code code : all) {
            codes.put(code.getId(), code.getValue());
        }
        return codes;
    }

    public List<DictTreeJson> getCodeTableList(String codeTableName){
        List<DictTreeJson> list;
        try {
            if ("continents".equals(codeTableName)) {

                list = getContinentsWithCountries();

            } else if("projectTypes".equals(codeTableName)) {

                list = getProjectTypesWithName();

            } else if("dispatchTypes".equals(codeTableName)) {

                list = getDispatchTypesWithDispatch();
            }
            else if ("provincesOnly".equals(codeTableName)) {

                list = getProvinces();

            } else if ("provincesUniversities".equals(codeTableName)) {

                list = getUniversities();

            } else if ("disciplines".equals(codeTableName)) {

                list = getSubjectsWithLeveThree();

            } else if ("abnormalTypes".equals(codeTableName)) {

                list = getAbnormalReasonsWithType();

            }else if ("belongings".equals(codeTableName)) {

                list = getBelongings();

            } else if ("tables".equals(codeTableName)) {

                // TODO. 动态查询时所需要的代码表
                list = null;

            } else {

                list = getTranslateDictByClassEn(codeTableName);

            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return list;
    }

    // 根据 classEn 获取 classId
    private String getClassIDByClassEn(String classEn) throws Exception {

        CodeTableClass codeTableClass = codeTableClassRepository.findByClassEn(classEn);

        if (codeTableClass == null) {
            throw new Exception("找不到{" + classEn + "}对应的 classID!");
        }

        return codeTableClass.getClassId();
    }

    // 根据 classId 获取相应的代码表
    private List<DictTreeJson> getTranslateDictByClassId(String classId) throws NoSuchDictTreeException {
        List<DictTreeJson> list = null;
        list = codeTableDAO.getCodeTable(classId);
        if (list == null) {
            throw new NoSuchDictTreeException("can not find translate dict by classId with " + classId);
        }
        return list;
    }

    // 根据 classEn 获得相应的代码表
    private List<DictTreeJson> getTranslateDictByClassEn(String classEn) throws Exception {

        String classId = this.getClassIDByClassEn(classEn);

        return this.getTranslateDictByClassId(classId);

    }

    // 获取大洲
    private List<DictTreeJson> getContinents() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.CONTINENTS, CodeTableDAO.CONTINENTS_LEVEL_ONE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("can not find continents.");
        }
        return jsonList;
    }

    // 获取大洲以及国别信息
    private List<DictTreeJson> getContinentsWithCountries() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.CONTINENTS, CodeTableDAO.CONTINENTS_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("can not find continents with their countries.");
        }
        return jsonList;
    }

    private List<DictTreeJson> getSubjectsWithLeveOne() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_ONE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getSubjectsWithLeveTwo() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getSubjectsWithLeveThree() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_THREE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getProjects() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_ONE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getProjectsWithType() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getProjectsWithTypeAndName() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_THREE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getProjectTypesWithName() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTTYPES_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getDispatchTypesWithDispatch() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.DISPATCHES, CodeTableDAO.DISPATCHES_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getAbnormalReasonsWithType() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.ABNORMAL, CodeTableDAO.ABNORMAL_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getProvinces() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.UNIVERSITIES, CodeTableDAO.UNIVERSITIES_LEVEL_ONE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getUniversities() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.UNIVERSITIES, CodeTableDAO.UNIVERSITIES_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    private List<DictTreeJson> getBelongings() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.DEPT, CodeTableDAO.DEPT_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

}
