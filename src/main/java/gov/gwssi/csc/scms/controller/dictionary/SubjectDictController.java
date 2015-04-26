package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.service.dictionary.SubjectDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangZhenghua on 2015/4/21.
 * 学科代码表Restful接口
 */

@RestController
@RequestMapping("api/codeTable")
public class SubjectDictController {
    @Autowired
    private SubjectDictService subjectDictService;

    private final String SUBJECT_LEVEL_ONE = "1";
    private final String SUBJECT_LEVEL_TWO = "2";
    private final String SUBJECT_LEVEL_THREE = "3";

    // 获取资源-学科三级列表
    @RequestMapping(value = "subjects",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getSubjectDict(){
        String subjectJsonData = "[]";
        try{
            subjectJsonData = subjectDictService.getSubjectDictJsonData(SUBJECT_LEVEL_THREE);
            System.out.println("SubjectDictController-->getSubjectDict方法中subectJsonData="+subjectJsonData);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return subjectJsonData;
    }
}
