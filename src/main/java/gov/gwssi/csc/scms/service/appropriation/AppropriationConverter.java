package gov.gwssi.csc.scms.service.appropriation;

import gov.gwssi.csc.scms.domain.appropriation.Appropriation;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/9/14.
 */
public class AppropriationConverter extends BaseService implements Converter<Appropriation, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(Appropriation appropriation) {
        Map<String, Object> target = new HashMap<String, Object>();
        target.put("id",appropriation.getId());
        target.put("sameId",appropriation.getSameId());
        target.put("no",appropriation.getNo());
        target.put("school",appropriation.getSchool());

        target.put("col134c",appropriation.getCol134c());
        target.put("col134m",appropriation.getCol134m());
        target.put("col135c",appropriation.getCol135c());
        target.put("col135m",appropriation.getCol135m());
        target.put("col137c",appropriation.getCol137c());
        target.put("col137m",appropriation.getCol137m());
        target.put("col139c",appropriation.getCol139c());
        target.put("col139m",appropriation.getCol139m());
        target.put("col141c",appropriation.getCol141c());
        target.put("col141m",appropriation.getCol141m());
        target.put("subtotal1",appropriation.getSubtotal1());

        target.put("col234c",appropriation.getCol234c());
        target.put("col234m",appropriation.getCol234m());
        target.put("col235c",appropriation.getCol235c());
        target.put("col235m",appropriation.getCol235m());
        target.put("col237c",appropriation.getCol237c());
        target.put("col237m",appropriation.getCol237m());
        target.put("col239c",appropriation.getCol239c());
        target.put("col239m",appropriation.getCol239m());
        target.put("col241c",appropriation.getCol241c());
        target.put("col241m",appropriation.getCol241m());
        target.put("subtotal2",appropriation.getSubtotal2());

        target.put("col334c",appropriation.getCol334c());
        target.put("col334m",appropriation.getCol334m());
        target.put("col335c",appropriation.getCol335c());
        target.put("col335m",appropriation.getCol335m());
        target.put("col337c",appropriation.getCol337c());
        target.put("col337m",appropriation.getCol337m());
        target.put("col339c",appropriation.getCol339c());
        target.put("col339m",appropriation.getCol339m());
        target.put("col341c",appropriation.getCol341c());
        target.put("col341m",appropriation.getCol341m());
        target.put("subtotal3",appropriation.getSubtotal3());
        return target;
    }
}
