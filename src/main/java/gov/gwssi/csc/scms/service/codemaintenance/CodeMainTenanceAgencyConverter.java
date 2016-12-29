package gov.gwssi.csc.scms.service.codemaintenance;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintenanceAgency;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TianJ on 2016/12/23.
 */
public class CodeMainTenanceAgencyConverter extends BaseService implements Converter<CodemaintenanceAgency, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(CodemaintenanceAgency codemaintenanceAgency) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",codemaintenanceAgency.getId());
        target.put("name",codemaintenanceAgency.getName());
        target.put("code",codemaintenanceAgency.getCode());
        target.put("enabled",codemaintenanceAgency.getEnabled());
        target.put("fullname",codemaintenanceAgency.getFullname());
        target.put("updated",codemaintenanceAgency.getUpdated());
        target.put("tableen",codemaintenanceAgency.getTableen());
        target.put("parentid",codemaintenanceAgency.getParentid());

        return target;
    }
}
