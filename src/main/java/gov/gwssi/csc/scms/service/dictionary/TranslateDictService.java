package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.TranslateDictDAO;
import gov.gwssi.csc.scms.domain.dictionary.CodeTableClass;
import gov.gwssi.csc.scms.domain.dictionary.TranslateDictJson;
import gov.gwssi.csc.scms.repository.dictionary.CodeTableClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("codeTableClassRepository")
    private CodeTableClassRepository codeTableClassRepository;

    // 根据classId获取相应的代码表
    public List<TranslateDictJson> getTranslateDictByClassId(String classId) throws NoSuchDictTreeException {
        List<TranslateDictJson> list = null;
        list = translateDictDAO.getTranslateDictByClassId(classId);
        if (list == null) {
            throw new NoSuchDictTreeException("can not find translate dict by classId with " + classId);
        }
        return list;
    }

    private String getClassIDByClassEn(String classEn) throws Exception {

        CodeTableClass codeTableClass = codeTableClassRepository.findByClassEn(classEn);

        if (codeTableClass == null) {
            throw new Exception("找不到{" + classEn + "}对应的 classID!");
        }

        return codeTableClass.getClassId();
    }

    // 根据 classEn 获得相应的代码表
    public List<TranslateDictJson> getTranslateDictByClassEn(String classEn) throws Exception {

        String classId = this.getClassIDByClassEn(classEn);

        return this.getTranslateDictByClassId(classId);

    }

}
