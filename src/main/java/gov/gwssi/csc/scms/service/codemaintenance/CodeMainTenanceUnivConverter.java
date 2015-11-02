package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionThird;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceUniv;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceUnivConverter extends BaseService implements Converter<CodemaintanenceUniv, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceUniv codemaintanenceUniv) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceUniv.getId());
        target.put("name",codemaintanenceUniv.getName());
        target.put("enabled",codemaintanenceUniv.getEnabled());
        target.put("fullname",codemaintanenceUniv.getFullname());
        target.put("updated",codemaintanenceUniv.getUpdated());
        target.put("tableen",codemaintanenceUniv.getTableen());
        target.put("parentid",codemaintanenceUniv.getParentid());
        target.put("type",codemaintanenceUniv.getType());
        target.put("admindept",codemaintanenceUniv.getAdmindept());
        target.put("code",codemaintanenceUniv.getCode());
        return target;
    }
}
