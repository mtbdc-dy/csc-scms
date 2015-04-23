package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.RegionDictDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangZhenghua on 2015/4/23.
 * region代码表service类
 */

@Service("regionDictService")
public class RegionDictService {

    @Autowired
    private RegionDictDAO regionDictDAO;

    // 根据region层次获取相应的大洲以及国别信息
    public String getRegionDictJsonData(String level){
        String jsonData = "";
        jsonData = regionDictDAO.getRegionDictJsonDataByLevel(level);
        System.out.println("RegionDictService-->getRegionDictJsonData方法jsonData == " + jsonData);
        return jsonData;
    }

}
