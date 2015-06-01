package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.ProjectDictDAO;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/23.
 * 项目代码表service类
 */

@Service("projectDictService")
public class ProjectDictService {

    @Autowired
    private ProjectDictDAO projectDictDAO;

    // 根据project层次获取相应的项目代码表(1为一级,2为二级)
    public List<DictTreeJson> getProjectDictTreeByLevel(String level) throws NoSuchDictTreeException{
        List<DictTreeJson> projectDictTree = null;
        projectDictTree = projectDictDAO.getProjectDictTreeByLevel(level);
        if(projectDictTree == null){
            throw new NoSuchDictTreeException("can not find the project with level " +level);
        }
        return projectDictTree;
    }
}
