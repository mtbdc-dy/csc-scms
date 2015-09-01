package gov.gwssi.csc.scms.service.importlog;

import gov.gwssi.csc.scms.domain.importlog.ImportLog;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/1.
 */
public class ImportLogConverter extends BaseService implements Converter<ImportLog, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(ImportLog importLog) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",importLog.getId());
        target.put("fileName",importLog.getFileName());
        target.put("cnt",importLog.getCnt());
        target.put("state",importLog.getState());
        target.put("importclass",importLog.getImportclass());
        target.put("createBy",importLog.getCreateBy());
        target.put("created",importLog.getCreated());
        target.put("error",importLog.getError());
        return target;
    }
}
