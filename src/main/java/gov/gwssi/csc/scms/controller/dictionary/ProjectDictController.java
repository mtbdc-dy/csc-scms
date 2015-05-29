package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.ProjectDictDAO;
import gov.gwssi.csc.scms.service.dictionary.ProjectDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangZhenghua on 2015/4/24.
 * 项目代码表Restful接口
 */

@RestController
@RequestMapping("/api/codeTable")
public class ProjectDictController {

    @Autowired
    private ProjectDictService projectDictService;

    private final String PROJECT_LEVEL_ONE = "1";
    private final String PROJECT_LEVEL_TWO = "2";
    private final String PROJECT_LEVEL_THREE = "3";

    // 获取资源-项目代码
    @RequestMapping(value = "project/{level}", method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getProject(@PathVariable String level) {
        String projectJsonData = "[]";
        try {
            projectJsonData = projectDictService.getProjectDictJsonData(level);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
        return projectJsonData;
    }

}
