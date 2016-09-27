package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectSecond;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectThird;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceProject3Converter extends BaseService implements Converter<CodemaintanenceProjectThird, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceProjectThird codemaintanenceProjectThird) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceProjectThird.getId());
        target.put("name",codemaintanenceProjectThird.getName());
        target.put("fundattr", codemaintanenceProjectThird.getFundattr());
        target.put("enabled",codemaintanenceProjectThird.getEnabled());
        target.put("fullname",codemaintanenceProjectThird.getFullname());
        target.put("updated",codemaintanenceProjectThird.getUpdated());
        target.put("tableen",codemaintanenceProjectThird.getTableen());
        target.put("parentid",codemaintanenceProjectThird.getParentid());
        target.put("projectcode", codemaintanenceProjectThird.getProjectCode());

        return target;
    }
}
