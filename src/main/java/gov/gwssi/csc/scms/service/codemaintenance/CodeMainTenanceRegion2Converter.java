package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionSecond;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceRegion2Converter extends BaseService implements Converter<CodemaintanenceRegionSecond, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceRegionSecond codemaintanenceRegionSecond) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceRegionSecond.getId());
        target.put("name",codemaintanenceRegionSecond.getName());
        target.put("enabled",codemaintanenceRegionSecond.getEnabled());
        target.put("fullname",codemaintanenceRegionSecond.getFullname());
        target.put("updated",codemaintanenceRegionSecond.getUpdated());
        target.put("tableen",codemaintanenceRegionSecond.getTableen());
        target.put("parentid",codemaintanenceRegionSecond.getParentid());

        return target;
    }
}
