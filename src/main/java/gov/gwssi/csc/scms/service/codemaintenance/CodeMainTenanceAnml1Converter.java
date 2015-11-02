package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceAnmlFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectFirst;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceAnml1Converter extends BaseService implements Converter<CodemaintanenceAnmlFirst, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceAnmlFirst codemaintanenceAnmlFirst) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceAnmlFirst.getId());
        target.put("name",codemaintanenceAnmlFirst.getName());
        target.put("enabled",codemaintanenceAnmlFirst.getEnabled());
        target.put("fullname",codemaintanenceAnmlFirst.getFullname());
        target.put("updated",codemaintanenceAnmlFirst.getUpdated());
        target.put("tableen",codemaintanenceAnmlFirst.getTableen());
        target.put("parentid",codemaintanenceAnmlFirst.getParentid());

        return target;
    }
}
