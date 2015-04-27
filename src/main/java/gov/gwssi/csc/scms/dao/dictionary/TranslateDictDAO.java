package gov.gwssi.csc.scms.dao.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dictionary.TranslateDictJson;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/4/27.
 * 转义代码表(一层)DAO层封装类
 */

@Service("translateDictDAO")
public class TranslateDictDAO extends BaseDAO{

    // 根据classId获取相应的TranslateDict List
    public List getTranslateDictListByClassId(String classId){
        List translateList = null;
        StringBuilder stringBuilder = new StringBuilder();
        if(classId != null && !classId.equals("")){
            stringBuilder.append("select classid,translateid,namech,nameen,enable from dim_translate where classid='"+classId+"'");
        }
        translateList = super.queryListBySql(stringBuilder.toString());
        return translateList;
    }

    // 根据classId转义后的JSONData
    public String getTranslateDictJsonDataByClassId(String classId){
        Map map = null;
        List translateList = getTranslateDictListByClassId(classId);
        List<TranslateDictJson> list = Lists.newArrayList();
        if(translateList != null && translateList.size()>0){
            for(int i=0;i<translateList.size();i++){
                map = (Map)translateList.get(i);
                TranslateDictJson translateDictJson = new TranslateDictJson();
                translateDictJson.setCode((map.get("TRANSLATEID") == null || map.get("TRANSLATEID").equals(""))?"":(String)map.get("TRANSLATEID"));
                translateDictJson.setValue((map.get("NAMECH") == null || map.get("NAMECH").equals(""))?"":(String)map.get("NAMECH"));
                if (map.get("ENABLED") != null && map.get("ENABLED").equals("1")) {
                    translateDictJson.setValid("true");
                } else {
                    translateDictJson.setValid("false");
                }
                list.add(translateDictJson);
            }
        }
        String jsonData = JsonMapper.getInstance().toJson(list);
        return jsonData;
    }

}
