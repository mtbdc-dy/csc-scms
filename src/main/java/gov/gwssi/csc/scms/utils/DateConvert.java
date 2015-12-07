package gov.gwssi.csc.scms.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tianj on 2015/12/4.
 */
public class DateConvert {
    public static Date convert(Date date,String type){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if("end".equals(type)){
            calendar.set(Calendar.HOUR, 24);
        }else{
            calendar.set(Calendar.HOUR, 0);
        }
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }
}
