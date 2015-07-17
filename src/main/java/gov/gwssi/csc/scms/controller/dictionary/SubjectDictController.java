package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.SubjectDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/21.
 * 学科代码表Restful接口
 */

@RestController
@RequestMapping("/api/codeTable")
public class SubjectDictController {
    @Autowired
    private SubjectDictService subjectDictService;

    // 获取资源-学科三级列表
    @RequestMapping(value = "subject/{level}",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<DictTreeJson> getSubjectDict(@PathVariable String level){
        List<DictTreeJson> projectDictTree = null;
        try{
            projectDictTree = subjectDictService.getSubjectDictTree(level);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return  projectDictTree;
    }
}
