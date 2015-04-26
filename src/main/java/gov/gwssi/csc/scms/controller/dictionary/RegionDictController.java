package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.service.dictionary.RegionDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangZhenghua on 2015/4/24.
 * 大洲以及国别代码表Restful接口
 */

@RestController
@RequestMapping("/api/codeTable")
public class RegionDictController {

    @Autowired
    private RegionDictService regionDictService;

    private final String REGION_LEVEL_ONE = "1";
    private final String REGION_LEVEL_TWO = "2";

    // 获取资源-大洲
    @RequestMapping(value="continent",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getContinent(){
        String continentJsonData = "[]";
        try {
            continentJsonData = regionDictService.getRegionDictJsonData(REGION_LEVEL_ONE);
        }catch(Exception e){
            e.printStackTrace();
            return "[]";
        }
        return continentJsonData;
    }

    // 获取资源-各大洲以及大洲下的国家
    @RequestMapping(value = "continent/country",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getContinentAndCountries(){
        String jsonData = "[]";
        try {
            jsonData = regionDictService.getRegionDictJsonData(REGION_LEVEL_TWO);
        }catch(Exception e){
            e.printStackTrace();
            return "[]";
        }
        return jsonData;
    }


}
