package gov.gwssi.csc.scms.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lei on 2015/6/5.
 */
public class TablesAndColumnsMap {


    public static final Map<String, String> tableMap = new HashMap<String, String>();
    static {
        tableMap.put("basicInfo","SCMS_BASIC_INFO");
        tableMap.put("discuss","SCMS_DISCUSS");
        tableMap.put("schoolRoll","SCMS_SCHOOLROLL");
        tableMap.put("profilesHistory","SCMS_PROFILES_HISTORY");
        tableMap.put("schoolfellow","SCMS_SCHOOL_FELLOW");
        tableMap.put("registrationInfo","SCMS_REGISTRATION_INFO");
        tableMap.put("insurance","SCMS_INSURANCE");
    }
}
