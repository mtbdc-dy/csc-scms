package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectSecond;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceSubject2Converter extends BaseService implements Converter<CodemaintanenceSubjectSecond, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceSubjectSecond codemaintanenceSubjectSecond) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceSubjectSecond.getId());
        target.put("name",codemaintanenceSubjectSecond.getName());
        target.put("enabled",codemaintanenceSubjectSecond.getEnabled());
        target.put("fullname",codemaintanenceSubjectSecond.getFullname());
        target.put("updated",codemaintanenceSubjectSecond.getUpdated());
        target.put("tableen",codemaintanenceSubjectSecond.getTableen());
        target.put("parentid",codemaintanenceSubjectSecond.getParentid());

        return target;
    }
}
