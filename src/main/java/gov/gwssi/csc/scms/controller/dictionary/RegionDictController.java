package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.RegionDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/24.
 * 大洲以及国别代码表Restful接口
 */

@RestController
@RequestMapping("/api/codeTable")
public class RegionDictController {

    @Autowired
    private RegionDictService regionDictService;

    // 获取资源-大洲及国家
    @RequestMapping(value="continent/{level}",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<DictTreeJson> getRegion(@PathVariable String level){
        List<DictTreeJson> regionDictTree = null;
        try {
            regionDictTree = regionDictService.getRegionDictTreeByLevel(level);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return regionDictTree;
    }

}
