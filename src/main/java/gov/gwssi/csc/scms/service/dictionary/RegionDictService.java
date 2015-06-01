package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.dao.dictionary.RegionDictDAO;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/23.
 * region代码表service类
 */

@Service("regionDictService")
public class RegionDictService {

    @Autowired
    private RegionDictDAO regionDictDAO;

    // 根据region层次获取相应的大洲以及国别信息
    public List<DictTreeJson> getRegionDictTreeByLevel(String level) throws  NoSuchDictTreeException{
         List<DictTreeJson> regionDictTree = null;
         regionDictTree = regionDictDAO.getRegionDictByLevel(level);
         if(regionDictTree == null){
             throw new NoSuchDictTreeException("can not find the region with level " +level);
         }
         return  regionDictTree;
    }

}
