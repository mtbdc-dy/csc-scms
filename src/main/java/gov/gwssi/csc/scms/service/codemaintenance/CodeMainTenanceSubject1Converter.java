package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceAnmlFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectFirst;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
public class CodeMainTenanceSubject1Converter extends BaseService
        implements Converter<CodemaintanenceSubjectFirst, Map<String, Object>>
{

    @Override
    public Map<String, Object> convert(CodemaintanenceSubjectFirst codemaintanenceSubjectFirst)
    {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id", codemaintanenceSubjectFirst.getId());
        target.put("name", codemaintanenceSubjectFirst.getName());
        target.put("fundstandard", codemaintanenceSubjectFirst.getFundstandard());
        target.put("enabled", codemaintanenceSubjectFirst.getEnabled());
        target.put("fullname", codemaintanenceSubjectFirst.getFullname());
        target.put("updated", codemaintanenceSubjectFirst.getUpdated());
        target.put("tableen", codemaintanenceSubjectFirst.getTableen());
        target.put("parentid", codemaintanenceSubjectFirst.getParentid());

        return target;
    }
}
