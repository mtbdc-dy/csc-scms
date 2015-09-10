package gov.gwssi.csc.scms.service.timeset;

import gov.gwssi.csc.scms.domain.universities.DimUniv;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/8/31.
 */
public class TimeSetConverter extends BaseService implements Converter<DimUniv, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(DimUniv dimUniv) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("univId",dimUniv.getUnivId());
        target.put("province",dimUniv.getProvince());
        target.put("newSetBy",dimUniv.getNewSetBy());
        target.put("newBegin",dimUniv.getNewBegin());
        target.put("newEnd",dimUniv.getNewEnd());

        target.put("newSeted",dimUniv.getNewSeted());
        target.put("oldSetBy",dimUniv.getOldSetBy());
        target.put("oldBegin",dimUniv.getOldBegin());
        target.put("oldEnd",dimUniv.getOldEnd());
        target.put("oldSeted",dimUniv.getOldSeted());

        return target;
    }
}
