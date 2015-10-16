package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceDeptFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceDeptSecond;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceDept2Converter extends BaseService implements Converter<CodemaintanenceDeptSecond, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceDeptSecond codemaintanenceDeptSecond) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceDeptSecond.getId());
        target.put("name",codemaintanenceDeptSecond.getName());
        target.put("enabled",codemaintanenceDeptSecond.getEnabled());
        target.put("fullname",codemaintanenceDeptSecond.getFullname());
        target.put("updated",codemaintanenceDeptSecond.getUpdated());
        target.put("tableen",codemaintanenceDeptSecond.getTableen());
        target.put("parentid",codemaintanenceDeptSecond.getParentid());

        return target;
    }
}
