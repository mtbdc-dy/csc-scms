package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceAnmlFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceAnmlSecond;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceAnml2Converter extends BaseService implements Converter<CodemaintanenceAnmlSecond, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceAnmlSecond codemaintanenceAnmlSecond) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceAnmlSecond.getId());
        target.put("name",codemaintanenceAnmlSecond.getName());
        target.put("enabled",codemaintanenceAnmlSecond.getEnabled());
        target.put("fullname",codemaintanenceAnmlSecond.getFullname());
        target.put("updated",codemaintanenceAnmlSecond.getUpdated());
        target.put("tableen",codemaintanenceAnmlSecond.getTableen());
        target.put("parentid",codemaintanenceAnmlSecond.getParentid());

        return target;
    }
}
