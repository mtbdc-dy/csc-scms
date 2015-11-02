package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionFirst;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceProject1Converter extends BaseService implements Converter<CodemaintanenceProjectFirst, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceProjectFirst codemaintanenceProjectFirst) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceProjectFirst.getId());
        target.put("name",codemaintanenceProjectFirst.getName());
        target.put("enabled",codemaintanenceProjectFirst.getEnabled());
        target.put("fullname",codemaintanenceProjectFirst.getFullname());
        target.put("updated",codemaintanenceProjectFirst.getUpdated());
        target.put("tableen",codemaintanenceProjectFirst.getTableen());
        target.put("parentid",codemaintanenceProjectFirst.getParentid());

        return target;
    }
}
