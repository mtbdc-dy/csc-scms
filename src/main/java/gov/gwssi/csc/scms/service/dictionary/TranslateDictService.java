package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.TranslateDictDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangZhenghua on 2015/4/27.
 * 转义代码表(一层)Service类
 */

@Service("translateDictService")
public class TranslateDictService {
    @Autowired
    private TranslateDictDAO translateDictDAO;

    // 根据classId获取相应的代码表
    public String getTranslateDictJsonData(String classId) {
        String jsonData = "[]";
        jsonData = translateDictDAO.getTranslateDictJsonDataByClassId(classId);
        System.out.println("TranslateDictService --> getTranslateDictJsonData方法jsonData= "+jsonData);
        return jsonData;
    }

}
