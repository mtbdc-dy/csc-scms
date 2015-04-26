package gov.gwssi.csc.scms.dao.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/4/21.
 * 学科代码表DAO层封装类
 */

@Service("subjectDictDAO")
public class SubjectDictDAO extends BaseDAO{

    private final String SUBJECT_LEVEL_ONE = "1";
    private final String SUBJECT_LEVEL_TWO = "2";
    private final String SUBJECT_LEVEL_THREE = "3";

    // 根据学科层级获取排序后的学科List
    public List getSubjectDictByLevel(String level){
        List subjectDictList;
        StringBuilder stringBuilder = new StringBuilder();
        if(level.equals(SUBJECT_LEVEL_ONE)){
            stringBuilder.append("select subjectid,subjectnamech,parentid,enabled from v_dim_spec_1");
        }else if(level.equals(SUBJECT_LEVEL_TWO)){
            stringBuilder.append("select subjectid,subjectnamech,parentid,enabled from v_dim_spec_2");
        }else if(level.equals(SUBJECT_LEVEL_THREE)){
            stringBuilder.append("select subjectid,subjectnamech,parentid,enabled from v_dim_spec_3");
        }
        subjectDictList = super.queryListBySql(stringBuilder.toString());
        return subjectDictList;
    }

    // 根据学科层级得到转义后的JSONData
    public String getSubjectDictJsonDataByLevel(String level){
        Map map = null;
        List subjectDictList = getSubjectDictByLevel(level);
        List<DictTreeJson> list = Lists.newArrayList();
        if(subjectDictList != null && subjectDictList.size()>0) {
            for (int i = 0; i < subjectDictList.size(); i++) {
                map = (Map) subjectDictList.get(i);
                DictTreeJson treeJson = new DictTreeJson();
                treeJson.setCode((map.get("SUBJECTID") == null || map.get("SUBJECTID").equals(""))? "" : (String) map.get("SUBJECTID"));
                treeJson.setValue((map.get("SUBJECTNAMECH") == null || map.get("SUBJECTNAMECH").equals(""))? "" : (String) map.get("SUBJECTNAMECH"));
                treeJson.setCodePid((map.get("PARENTID") == null || map.get("PARENTID").equals(""))? "" : (String) map.get("PARENTID"));
                if (map.get("ENABLED") != null && map.get("ENABLED").equals("1")) {
                    treeJson.setValid("true");
                } else {
                    treeJson.setValid("false");
                }
                list.add(treeJson);
            }
        }
        List<DictTreeJson> dictList = DictTreeJson.formatTree(list);
        String jsonData = JsonMapper.getInstance().toJson(dictList);
        return jsonData;
    }

}
