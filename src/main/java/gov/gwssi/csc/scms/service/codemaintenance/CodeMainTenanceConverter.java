package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodeMainTenance;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/10.
 */
public class CodeMainTenanceConverter extends BaseService implements Converter<CodeMainTenance, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(CodeMainTenance codeMainTenance) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("seq",codeMainTenance.getSeq());
        target.put("tableEn",codeMainTenance.getTableEn());
        target.put("flag",codeMainTenance.getFlag());
        target.put("tableCh",codeMainTenance.getTableCh());
        target.put("category",codeMainTenance.getCategory());
        target.put("type",codeMainTenance.getType());


        return target;
    }
}
