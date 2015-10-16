package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceDeptFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectFirst;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceDept1Converter extends BaseService implements Converter<CodemaintanenceDeptFirst, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceDeptFirst codemaintanenceDeptFirst) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceDeptFirst.getId());
        target.put("name",codemaintanenceDeptFirst.getName());
        target.put("enabled",codemaintanenceDeptFirst.getEnabled());
        target.put("fullname",codemaintanenceDeptFirst.getFullname());
        target.put("updated",codemaintanenceDeptFirst.getUpdated());
        target.put("tableen",codemaintanenceDeptFirst.getTableen());
        target.put("parentid",codemaintanenceDeptFirst.getParentid());

        return target;
    }
}
