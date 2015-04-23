package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.SubjectDictDAO;
import gov.gwssi.csc.scms.service.BaseService;
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

    // 根据学科层级获取相应的学科代码表信息(level:1为一级，2为二级，3为三级)
    public String getSubjectDictJsonData(String level){
       String jsonData = "";
       jsonData = subjectDictDAO.getSubjectDictJsonDataByLevel(level);
       System.out.println("SubjectDictService-->getSubjectDict方法jsonData == " + jsonData);
       return  jsonData;
    }

}
