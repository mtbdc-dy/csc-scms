package gov.gwssi.csc.scms.dao.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wang Zishi on 15/7/22.
 * 代码表DAO
 */

@Service("codeTableDAO")
public class CodeTableDAO extends BaseDAO {

    public static final String PROJECTS = "projectId";
    public static final String PROJECTS_LEVEL_ONE = "v_dim_project_1";
    public static final String PROJECTS_LEVEL_TWO = "v_dim_project_2";
    public static final String PROJECTS_LEVEL_THREE = "v_dim_project_3";

    public static final String CONTINENTS = "regionId";
    public static final String CONTINENTS_LEVEL_ONE = "v_dim_region_1";
    public static final String CONTINENTS_LEVEL_TWO = "v_dim_region_2";

    public static final String SUBJECTS = "subjectId";
    public static final String SUBJECTS_LEVEL_ONE = "v_dim_spec_1";
    public static final String SUBJECTS_LEVEL_TWO = "v_dim_spec_2";
    public static final String SUBJECTS_LEVEL_THREE = "v_dim_spec_3";

    public static final String ABNORMAL = "anmlId";
    public static final String ABNORMAL_LEVEL_ONE = "v_dim_anml_1";
    public static final String ABNORMAL_LEVEL_TWO = "v_dim_anml_2";

    public static final String UNIVERSITIES = "univId";
    public static final String UNIVERSITIES_LEVEL_ONE = "v_dim_univ_1";
    public static final String UNIVERSITIES_LEVEL_TWO = "v_dim_univ_2";

    public static final String DEPT = "deptId";
    public static final String DEPT_LEVEL_ONE = "v_dim_dept_1";
    public static final String DEPT_LEVEL_TWO = "v_dim_dept_2";

    public static final String TRANSLATE = "translateId";


    public List<DictTreeJson> getCodeTableByLevel(String codeTableName, String level) {
        String sql = "select " + codeTableName + ", nameCh, parentId, enabled from " + level;
        List<Map> codeTableList = super.queryListBySql(sql);
        List<DictTreeJson> codeTable = Lists.newArrayList();

        convertList(codeTableList, codeTable, codeTableName);

        return DictTreeJson.formatTree(codeTable);
    }

    public List<DictTreeJson> getCodeTable(String classId) {
        String sql = "select classId, translateId, nameCh, nameEn, enabled from dim_translate where classId='" + classId + "'";
        List<Map> codeTableList = super.queryListBySql(sql);
        List<DictTreeJson> codeTable = Lists.newArrayList();

        convertList(codeTableList, codeTable, TRANSLATE);

        return codeTable;
    }

    private void convertList(List<Map> codeTableList, List<DictTreeJson> codeTable, String codeTableName) {
        if (codeTableList != null && codeTableList.size() > 0) {
            final String Id = codeTableName.toUpperCase();
            final String Name = "nameCh".toUpperCase();
            final String ParentId = "parentId".toUpperCase();
            final String Enabled = "Enabled".toUpperCase();

            for (Map map : codeTableList) {
                String id = (String) map.get(Id);
                String value = (String) map.get(Name);
                String pid = (String) map.get(ParentId);
                String enabled = (String) map.get(Enabled);

                DictTreeJson code = new DictTreeJson();

                boolean idIsEmpty = id == null || id.isEmpty();
                boolean valueIsEmpty = value == null || value.isEmpty();
                boolean pidIsEmpty = pid == null || pid.isEmpty();
                boolean isEnabled = enabled != null && map.get(Enabled).equals("1");

                id = idIsEmpty ? "" : id;
                value = valueIsEmpty ? "" : value;
                pid = pidIsEmpty ? "" : pid;

                code.setCode(id);
                code.setValue(value);
                code.setCodePid(pid);
                code.setValid(isEnabled);

                codeTable.add(code);

            }
        }
    }



}
