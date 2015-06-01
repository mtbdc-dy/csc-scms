package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.TranslateDictDAO;
import gov.gwssi.csc.scms.domain.dictionary.TranslateDictJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/27.
 * 转义代码表(一层)Service类
 */

@Service("translateDictService")
public class TranslateDictService {
    @Autowired
    private TranslateDictDAO translateDictDAO;

    // 根据classId获取相应的代码表
    public List<TranslateDictJson> getTranslateDictByClassId(String classId) throws NoSuchDictTreeException{
        List<TranslateDictJson> list = null;
        list = translateDictDAO.getTranslateDictByClassId(classId);
        if(list == null){
            throw new NoSuchDictTreeException("can not find translate dict by classId with "+classId);
        }
        return list;
    }

}
