package gov.gwssi.csc.scms.dao.dictionary;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import org.junit.Test;

import java.util.List;

/**
 * Created by wang Zishi on 15/7/22.
 * 单元测试
 */
public class CodeTableDAOTest extends UnitTestBase {

    @Test
    public void testGetCodeTableByLevel() throws Exception {
        CodeTableDAO codeTableDAO = getBean("codeTableDAO");

        List<DictTreeJson> continentsLevelOne = codeTableDAO.getCodeTableByLevel(CodeTableDAO.CONTINENTS, CodeTableDAO.CONTINENTS_LEVEL_ONE);
        List<DictTreeJson> continentsLevelTwo = codeTableDAO.getCodeTableByLevel(CodeTableDAO.CONTINENTS, CodeTableDAO.CONTINENTS_LEVEL_TWO);

        List<DictTreeJson> projectsLevelOne = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_ONE);
        List<DictTreeJson> projectsLevelTwo = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_TWO);
        List<DictTreeJson> projectsLevelThree = codeTableDAO.getCodeTableByLevel(CodeTableDAO.PROJECTS, CodeTableDAO.PROJECTS_LEVEL_THREE);

        List<DictTreeJson> subjectsLevelOne = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_ONE);
        List<DictTreeJson> subjectsLevelTwo = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_TWO);
        List<DictTreeJson> subjectsLevelThree = codeTableDAO.getCodeTableByLevel(CodeTableDAO.SUBJECTS, CodeTableDAO.SUBJECTS_LEVEL_THREE);

        List<DictTreeJson> abnormalLevelOne = codeTableDAO.getCodeTableByLevel(CodeTableDAO.ABNORMAL, CodeTableDAO.ABNORMAL_LEVEL_ONE);
        List<DictTreeJson> abnormalLevelTwo = codeTableDAO.getCodeTableByLevel(CodeTableDAO.ABNORMAL, CodeTableDAO.ABNORMAL_LEVEL_TWO);

        List<DictTreeJson> universitiesLevelOne = codeTableDAO.getCodeTableByLevel(CodeTableDAO.UNIVERSITIES, CodeTableDAO.UNIVERSITIES_LEVEL_ONE);
        List<DictTreeJson> universitiesLevelTwo = codeTableDAO.getCodeTableByLevel(CodeTableDAO.UNIVERSITIES, CodeTableDAO.UNIVERSITIES_LEVEL_TWO);

        List<DictTreeJson> gender = codeTableDAO.getCodeTable("AA");

        System.out.println("continentsLevelOne = " + continentsLevelOne);
        System.out.println("continentsLevelTwo = " + continentsLevelTwo);

        System.out.println("projectsLevelOne = " + projectsLevelOne);
        System.out.println("projectsLevelTwo = " + projectsLevelTwo);
        System.out.println("projectsLevelThree = " + projectsLevelThree);

        System.out.println("subjectsLevelOne = " + subjectsLevelOne);
        System.out.println("subjectsLevelTwo = " + subjectsLevelTwo);
        System.out.println("subjectsLevelThree = " + subjectsLevelThree);

        System.out.println("abnormalLevelOne = " + abnormalLevelOne);
        System.out.println("abnormalLevelTwo = " + abnormalLevelTwo);

        System.out.println("universitiesLevelOne = " + universitiesLevelOne);
        System.out.println("universitiesLevelTwo = " + universitiesLevelTwo);

        System.out.println("gender = " + gender);

    }
}