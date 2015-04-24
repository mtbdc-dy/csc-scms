package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.ProjectDictDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangZhenghua on 2015/4/23.
 * 项目代码表service类
 */

@Service("projectDictService")
public class ProjectDictService {

    @Autowired
    private ProjectDictDAO projectDictDAO;

    // 根据project层次获取相应的项目代码表(1为一级,2为二级)
    public String getProjectDictJsonData(String level){
        String jsonData = "[]";
        jsonData = projectDictDAO.getProjectDictJsonDataByLevel(level);
        System.out.println("ProjectDictService-->getProjectDictJsonData方法jsonData == " + jsonData);
        return jsonData;
    }
}
