package gov.gwssi.csc.scms.dao.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/4/23.
 * 大洲代码表DAO层封装类
 */

@Service("regionDictDAO")
public class RegionDictDAO extends BaseDAO{

    private static final String REGION_LEVEL_ONE = "1";
    private static final String REGION_LEVEL_TWO = "2";

    // 根据region层次获取排序后的List
    public List getRegionDictByLevel(String level){
        List regionList = null;
        StringBuilder stringBuilder = new StringBuilder();
        if(level.equals(REGION_LEVEL_ONE)){
            stringBuilder.append("select regionid,namech,parentid,enabled from v_dim_region_1");
        }else if(level.equals(REGION_LEVEL_TWO)){
            stringBuilder.append("select regionid,namech,parentid,enabled from v_dim_region_2");
        }
        regionList = super.queryListBySql(stringBuilder.toString());
        return regionList;
    }

    // 根据region层级得到转义后的List
    public List<DictTreeJson> getRegionDictTreeByLevel(String level){
        Map map = null;
        List regionList = getRegionDictByLevel(level);
        List<DictTreeJson> list = Lists.newArrayList();
        if(regionList != null && regionList.size()>0){
            for(int i=0;i<regionList.size();i++){
                map = (Map)regionList.get(i);
                DictTreeJson dictTreeJson = new DictTreeJson();
                dictTreeJson.setCode((map.get("REGIONID") == null || map.get("REGIONID").equals(""))?"":(String)map.get("REGIONID"));
                dictTreeJson.setValue((map.get("NAMECH") == null || map.get("NAMECH").equals(""))? "" : (String) map.get("NAMECH"));
                dictTreeJson.setCodePid((map.get("PARENTID") == null || map.get("PARENTID").equals(""))? "" : (String) map.get("PARENTID"));
                if (map.get("ENABLED") != null && map.get("ENABLED").equals("1")) {
                    dictTreeJson.setValid(true);
                } else {
                    dictTreeJson.setValid(false);
                }
                list.add(dictTreeJson);
            }
        }
        List<DictTreeJson> dictList = DictTreeJson.formatTree(list);
        return dictList;
    }

}
