package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceTranslate;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceTranslateConverter extends BaseService implements Converter<CodemaintanenceTranslate, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(CodemaintanenceTranslate codemaintanenceTranslate) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintanenceTranslate.getId());
        target.put("name",codemaintanenceTranslate.getName());
        target.put("enabled",codemaintanenceTranslate.getEnabled());
        target.put("fullname",codemaintanenceTranslate.getFullname());
        target.put("updated",codemaintanenceTranslate.getUpdated());
        target.put("tableen",codemaintanenceTranslate.getTableen());
        target.put("parentid",codemaintanenceTranslate.getParentid());

        return target;
    }
}
