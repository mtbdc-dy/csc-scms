package gov.gwssi.csc.scms.service.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dictionary.SubjectDictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.junit.Test;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/4/20.
 */
public class SubjectDictServiceTest  extends UnitTestBase {

    @Test
    public void getSubjectInfo(){
        SubjectDictService subjectDictService = getBean("subjectDictService");
        subjectDictService.getSubjectDictInfo();
    }

    @Test
    public void getSubjectDict(){
        SubjectDictService subjectDictService = getBean("subjectDictService");
        long begin = System.currentTimeMillis();;
        List subjectDictList = subjectDictService.getSubjectDict();
        List<SubjectDictTreeJson> list = Lists.newArrayList();
        for(int i=0;i<subjectDictList.size();i++){
            Map map = (Map)subjectDictList.get(i);
            SubjectDictTreeJson treeJson = new SubjectDictTreeJson();
            treeJson.setCode((String) map.get("SUBJECT_ID") == null ? "" : (String) map.get("SUBJECT_ID"));
            treeJson.setValue((String) map.get("SUBJECT_NAME_CH") == null ? "" : (String) map.get("SUBJECT_NAME_CH"));
            treeJson.setCodePid(map.get("PARENT_ID") == null ? "" : (String) map.get("PARENT_ID"));
            if(map.get("ENABLED").equals("1")){
                treeJson.setValid("true");
            }else{
                treeJson.setValid("false");
            }
            list.add(treeJson);
        }
            List<SubjectDictTreeJson> dictList = SubjectDictTreeJson.formatTree(list);
            String json = JsonMapper.getInstance().toJson(dictList);
            System.out.println("json======"+json);
            long end = System.currentTimeMillis();
            System.out.println("cost time="+(end-begin));

    }

    @Test
    public void getSubjectDictBySpec1(){
        SubjectDictService subjectDictService = getBean("subjectDictService");
        List subjectDictList = subjectDictService.getSubjectDictBySpec1();
        List<SubjectDictTreeJson> list = Lists.newArrayList();
        for(int i=0;i<subjectDictList.size();i++){
            Map map = (Map)subjectDictList.get(i);
            SubjectDictTreeJson treeJson = new SubjectDictTreeJson();
            treeJson.setCode((String) map.get("SUBJECT_ID") == null ? "" : (String) map.get("SUBJECT_ID"));
            treeJson.setValue((String) map.get("SUBJECT_NAME_CH") == null ? "" : (String) map.get("SUBJECT_NAME_CH"));
            treeJson.setCodePid(map.get("PARENT_ID") == null ? "" : (String) map.get("PARENT_ID"));
            if(map.get("ENABLED").equals("1")){
                treeJson.setValid("true");
            }else{
                treeJson.setValid("false");
            }
            list.add(treeJson);
        }
        List<SubjectDictTreeJson> dictList = SubjectDictTreeJson.formatTree(list);
        String json = JsonMapper.getInstance().toJson(dictList);
        System.out.println("json****======"+json);
    }


}
