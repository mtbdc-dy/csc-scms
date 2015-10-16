package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectSecond;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectThird;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceSubject3Converter extends BaseService implements Converter<CodemaintanenceSubjectThird, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceSubjectThird codemaintanenceSubjectThird) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceSubjectThird.getId());
        target.put("name",codemaintanenceSubjectThird.getName());
        target.put("enabled",codemaintanenceSubjectThird.getEnabled());
        target.put("fullname",codemaintanenceSubjectThird.getFullname());
        target.put("updated",codemaintanenceSubjectThird.getUpdated());
        target.put("tableen",codemaintanenceSubjectThird.getTableen());
        target.put("parentid",codemaintanenceSubjectThird.getParentid());

        return target;
    }
}
