package gov.gwssi.csc.scms.service.log;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/6.
 */
public class OperationLogConverter extends BaseService implements Converter<OperationLog, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(OperationLog operationLog) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("module",operationLog.getModule());
        target.put("cscId",operationLog.getCscId());
        target.put("passportName",operationLog.getPassportName());
        target.put("optType",operationLog.getOptType());
        target.put("tableCH",operationLog.getTableCH());

        target.put("columnCH",operationLog.getColumnCH());
        target.put("before",operationLog.getBefore());
        target.put("after",operationLog.getAfter());
        target.put("createBy",operationLog.getCreateBy());
        target.put("createD",operationLog.getCreateD());

        return target;
    }
}
