package gov.gwssi.csc.scms.dao.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/4/23.
 * 项目代码表DAO层封装类
 */

@Service("projectDictDAO")
public class ProjectDictDAO extends BaseDAO {

    private static final String PROJECT_LEVEL_ONE = "1";
    private static final String PROJECT_LEVEL_TWO = "2";
    private static final String PROJECT_LEVEL_THREE = "3";

    // 根据project层级获取排序后的项目List
    public List getProjectDictByLevel(String level) {
        List projectList = null;
        StringBuilder stringBuilder = new StringBuilder();
        if (level.equals(PROJECT_LEVEL_ONE)) {
            stringBuilder.append("select regionid,namech,parentid,enabled from v_dim_project_1");
        } else if (level.equals(PROJECT_LEVEL_TWO)) {
            stringBuilder.append("select regionid,namech,parentid,enabled from v_dim_project_2");
        } else if (level.equals(PROJECT_LEVEL_THREE)) {
            stringBuilder.append("select regionid,namech,parentid,enabled from v_dim_project_3");
        }
        projectList = super.queryListBySql(stringBuilder.toString());
        return projectList;
    }

    // 根据project层级得到转义后的List
    public List<DictTreeJson> getProjectDictTreeByLevel(String level) {
        Map map = null;
        List projectList = getProjectDictByLevel(level);
        List<DictTreeJson> list = Lists.newArrayList();
        if (projectList != null && projectList.size() > 0) {
            for (int i = 0; i < projectList.size(); i++) {
                map = (Map) projectList.get(i);
                DictTreeJson dictTreeJson = new DictTreeJson();
                dictTreeJson.setCode((map.get("REGIONID") == null || map.get("REGIONID").equals("")) ? "" : (String) map.get("REGIONID"));
                dictTreeJson.setValue((map.get("NAMECH") == null || map.get("NAMECH").equals("")) ? "" : (String) map.get("NAMECH"));
                dictTreeJson.setCodePid((map.get("PARENTID") == null || map.get("PARENTID").equals("")) ? "" : (String) map.get("PARENTID"));
                if (map.get("ENABLED") != null && map.get("ENABLED").equals("1")) {
                    dictTreeJson.setValid(true);
                } else {
                    dictTreeJson.setValid(false);
                }
                list.add(dictTreeJson);
            }
        }
        List<DictTreeJson> dictList = DictTreeJson.formatTree(list);
//      String jsonData = JsonMapper.getInstance().toJson(dictList);
        return dictList;
    }

}