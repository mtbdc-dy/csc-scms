package gov.gwssi.csc.scms.service.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.dao.dictionary.SubjectDictDAO;
import gov.gwssi.csc.scms.domain.dictionary.SubjectDictTreeJson;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/4/20.
 * 学科代码表Service类
 */

@Service("subjectDictService")
public class SubjectDictService extends BaseService{

    @Autowired
    private SubjectDictDAO subjectDictDAO;

    public String getSubjectDictInfo(){
       String jsonData = subjectDictDAO.getSubjectDictJsonData();
       System.out.println("test jsonData == " + jsonData);
       return  jsonData;
    }

    public List getSubjectDict(){
        List subjectDictList;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select subject_id,subject_name_ch,parent_id,enabled from v_dim_spec_3");
        subjectDictList = super.getBaseDao().queryListBySql(stringBuilder.toString());
        return subjectDictList;
    }

    public List getSubjectDictBySpec1(){
        List subjectDictList;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select subject_id,subject_name_ch,parent_id,enabled from v_dim_spec_1");
        subjectDictList = super.getBaseDao().queryListBySql(stringBuilder.toString());
        return subjectDictList;
    }

    public String getSubjectDictJsonDataBySpec1(){
        List subjecDictList = null;
        String jsonData = "";
        subjecDictList = getSubjectDictBySpec1();
        jsonData = obtainSubjectDictJsonData(subjecDictList);
        return jsonData;
    }

    public List getSubjectDictBySpec2(){
        List subjectDictList;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select subject_id,subject_name_ch,parent_id,enabled from v_dim_spec_2");
        subjectDictList = super.getBaseDao().queryListBySql(stringBuilder.toString());
        return subjectDictList;
    }

    public String getSubjectDictJsonDataBySpec2(){
        List subjecDictList = null;
        String jsonData = "";
        subjecDictList = getSubjectDictBySpec1();
        jsonData = obtainSubjectDictJsonData(subjecDictList);
        return jsonData;
    }

    public String getSubjectDictJsonData(){
        List subjectDictList = getSubjectDict();
        List<SubjectDictTreeJson> list = Lists.newArrayList();
        if(subjectDictList != null && subjectDictList.size()>0) {
            for (int i = 0; i < subjectDictList.size(); i++) {
                Map map = (Map) subjectDictList.get(i);
                SubjectDictTreeJson treeJson = new SubjectDictTreeJson();
                treeJson.setCode((String) map.get("SUBJECT_ID") == null ? "" : (String) map.get("SUBJECT_ID"));
                treeJson.setValue((String) map.get("SUBJECT_NAME_CH") == null ? "" : (String) map.get("SUBJECT_NAME_CH"));
                treeJson.setCodePid(map.get("PARENT_ID") == null ? "" : (String) map.get("PARENT_ID"));
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

    public String obtainSubjectDictJsonData(List subjectDictList){
        List<SubjectDictTreeJson> list = Lists.newArrayList();
        if(subjectDictList != null && subjectDictList.size()>0){
            for (int i = 0; i < subjectDictList.size(); i++) {
                Map map = (Map) subjectDictList.get(i);
                SubjectDictTreeJson treeJson = new SubjectDictTreeJson();
                treeJson.setCode((String) map.get("SUBJECT_ID") == null ? "" : (String) map.get("SUBJECT_ID"));
                treeJson.setValue((String) map.get("SUBJECT_NAME_CH") == null ? "" : (String) map.get("SUBJECT_NAME_CH"));
                treeJson.setCodePid(map.get("PARENT_ID") == null ? "" : (String) map.get("PARENT_ID"));
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
