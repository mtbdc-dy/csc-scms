package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectSecond;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceProject2Converter extends BaseService implements Converter<CodemaintanenceProjectSecond, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceProjectSecond codemaintanenceProjectSecond) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceProjectSecond.getId());
        target.put("name",codemaintanenceProjectSecond.getName());
        target.put("enabled",codemaintanenceProjectSecond.getEnabled());
        target.put("fullname",codemaintanenceProjectSecond.getFullname());
        target.put("updated",codemaintanenceProjectSecond.getUpdated());
        target.put("tableen",codemaintanenceProjectSecond.getTableen());
        target.put("parentid",codemaintanenceProjectSecond.getParentid());

        return target;
    }
}
