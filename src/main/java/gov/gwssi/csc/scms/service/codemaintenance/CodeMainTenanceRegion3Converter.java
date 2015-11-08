package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionSecond;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionThird;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceRegion3Converter extends BaseService implements Converter<CodemaintanenceRegionThird, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceRegionThird codemaintanenceRegionThird) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceRegionThird.getId());
        target.put("name",codemaintanenceRegionThird.getName());
        target.put("enabled",codemaintanenceRegionThird.getEnabled());
        target.put("fullname",codemaintanenceRegionThird.getFullname());
        target.put("updated",codemaintanenceRegionThird.getUpdated());
        target.put("tableen",codemaintanenceRegionThird.getTableen());
        target.put("parentid",codemaintanenceRegionThird.getParentid());

        return target;
    }
}
