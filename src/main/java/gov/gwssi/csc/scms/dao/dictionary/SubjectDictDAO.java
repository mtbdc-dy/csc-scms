package gov.gwssi.csc.scms.dao.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dictionary.SubjectDictTreeJson;
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
        List subjectDictList = getSubjectDictByLevel(level);
        List<SubjectDictTreeJson> list = Lists.newArrayList();
        if(subjectDictList != null && subjectDictList.size()>0) {
            for (int i = 0; i < subjectDictList.size(); i++) {
                Map map = (Map) subjectDictList.get(i);
                SubjectDictTreeJson treeJson = new SubjectDictTreeJson();
                treeJson.setCode((String) map.get("SUBJECTID") == null ? "" : (String) map.get("SUBJECTID"));
                treeJson.setValue((String) map.get("SUBJECTNAMECH") == null ? "" : (String) map.get("SUBJECTNAMECH"));
                treeJson.setCodePid(map.get("PARENTID") == null ? "" : (String) map.get("PARENTID"));
                if (map.get("ENABLED").equals("1")) {
                    treeJson.setValid("true");
                } else {
                    treeJson.setValid("false");
                }
                list.add(treeJson);
            }
        }
        List<SubjectDictTreeJson> dictList = SubjectDictTreeJson.formatTree(list);
        String jsonData = JsonMapper.getInstance().toJson(dictList);
        return jsonData;
    }

}
