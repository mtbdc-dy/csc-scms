package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionFirst;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceRegion1Converter extends BaseService implements Converter<CodemaintanenceRegionFirst, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceRegionFirst codemaintanenceRegionFirst) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceRegionFirst.getId());
        target.put("name",codemaintanenceRegionFirst.getName());
        target.put("enabled",codemaintanenceRegionFirst.getEnabled());
        target.put("fullname",codemaintanenceRegionFirst.getFullname());
        target.put("updated",codemaintanenceRegionFirst.getUpdated());
        target.put("tableen",codemaintanenceRegionFirst.getTableen());
        target.put("parentid",codemaintanenceRegionFirst.getParentid());

        return target;
    }
}
