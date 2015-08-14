package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.CodeTableDAO;
import gov.gwssi.csc.scms.domain.dictionary.CodeTableClass;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.repository.dictionary.CodeTableClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 根据 classEn 获取 classId
    private String getClassIDByClassEn(String classEn) throws Exception {

        CodeTableClass codeTableClass = codeTableClassRepository.findByClassEn(classEn);

        if (codeTableClass == null) {
            throw new Exception("找不到{" + classEn + "}对应的 classID!");
        }

        return codeTableClass.getClassId();
    }

    // 根据 classId 获取相应的代码表
    public List<DictTreeJson> getTranslateDictByClassId(String classId) throws NoSuchDictTreeException {
        List<DictTreeJson> list = null;
        list = codeTableDAO.getCodeTable(classId);
        if (list == null) {
            throw new NoSuchDictTreeException("can not find translate dict by classId with " + classId);
        }
        return list;
    }

    // 根据 classEn 获得相应的代码表
    public List<DictTreeJson> getTranslateDictByClassEn(String classEn) throws Exception {

        String classId = this.getClassIDByClassEn(classEn);

        return this.getTranslateDictByClassId(classId);

    }

    // 获取大洲
    public List<DictTreeJson> getContinents() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.CONTINENTS, CodeTableDAO.CONTINENTS_LEVEL_ONE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("can not find continents.");
        }
        return jsonList;
    }

    // 获取大洲以及国别信息
    public List<DictTreeJson> getContinentsWithCountries() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.CONTINENTS, CodeTableDAO.CONTINENTS_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("can not find continents with their countries.");
        }
        return jsonList;
    }

    public List<DictTreeJson> getSubjectsWithLeveOne() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_ONE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    public List<DictTreeJson> getSubjectsWithLeveTwo() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    public List<DictTreeJson> getSubjectsWithLeveThree() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_THREE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    public List<DictTreeJson> getProjects() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_ONE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    public List<DictTreeJson> getProjectsWithType() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    public List<DictTreeJson> getProjectsWithTypeAndName() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_THREE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    public List<DictTreeJson> getAbnormalReasonsWithType() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.ABNORMAL, CodeTableDAO.ABNORMAL_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    public List<DictTreeJson> getProvinces() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.UNIVERSITIES, CodeTableDAO.UNIVERSITIES_LEVEL_ONE);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

    public List<DictTreeJson> getUniversities() throws NoSuchDictTreeException {
        List<DictTreeJson> jsonList = codeTableDAO.getCodeTableByLevel(CodeTableDAO.UNIVERSITIES, CodeTableDAO.UNIVERSITIES_LEVEL_TWO);
        if (jsonList == null) {
            throw new NoSuchDictTreeException("");
        }
        return jsonList;
    }

//    public list<DictTreeJson> getProjectsWithTypeAndName


//    // 根据region层次获取相应的大洲以及国别信息
//    public List<DictTreeJson> getRegionDictTreeByLevel(String level) throws  NoSuchDictTreeException{
//        List<DictTreeJson> regionDictTree = null;
//        regionDictTree = regionDictDAO.getRegionDictTreeByLevel(level);
//        if(regionDictTree == null){
//            throw new NoSuchDictTreeException("can not find the region with level " +level);
//        }
//        return  regionDictTree;
//    }

}
