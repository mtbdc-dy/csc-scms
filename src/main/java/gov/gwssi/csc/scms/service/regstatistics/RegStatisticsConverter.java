package gov.gwssi.csc.scms.service.regstatistics;

import gov.gwssi.csc.scms.domain.regstatistics.RegStatistics;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/2.
 */
public class RegStatisticsConverter extends BaseService implements Converter<RegStatistics, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(RegStatistics regStatistics) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",regStatistics.getId());
        target.put("sameId",regStatistics.getSameId());
        target.put("no",regStatistics.getNo());
        target.put("province",regStatistics.getProvince());
        target.put("university",regStatistics.getUniversity());
        target.put("totalNum",regStatistics.getTotalNum());
        target.put("registeredNum",regStatistics.getRegisteredNum());
        target.put("giveUpNum",regStatistics.getGiveUpNum());
        target.put("unhandledNum",regStatistics.getUnhandledNum());
        return target;
    }
}
