package gov.gwssi.csc.scms.service.monitor;

import gov.gwssi.csc.scms.domain.monitor.Monitor;
import gov.gwssi.csc.scms.domain.monitor.MonitorDay;
import gov.gwssi.csc.scms.domain.monitor.MonitorMonth;
import gov.gwssi.csc.scms.domain.monitor.MonitorWeek;
import gov.gwssi.csc.scms.repository.monitor.MonitorDayRepository;
import gov.gwssi.csc.scms.repository.monitor.MonitorMonthRepository;
import gov.gwssi.csc.scms.repository.monitor.MonitorRepository;
import gov.gwssi.csc.scms.repository.monitor.MonitorWeekRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MonitorService extends BaseService {

    @Autowired
    @Qualifier("monitorRepository")
    private MonitorRepository monitorRepository;

    @Autowired
    @Qualifier("monitorDayRepository")
    private MonitorDayRepository monitorDayRepository;

    @Autowired
    @Qualifier("monitorMonthRepository")
    private MonitorMonthRepository monitorMonthRepository;

    @Autowired
    @Qualifier("monitorWeekRepository")
    private MonitorWeekRepository monitorWeekRepository;


    //动态监控
    public Monitor monitorUsersCount(Monitor monitor){

        monitor.setId(getBaseDao().getIdBySequence("seq_monitor"));
        return monitorRepository.save(monitor);
    }

    //查询动态监控数据 以小时为单位
    public List<Monitor> getMonitors(String beginTime,String endTime){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            if (isNull(beginTime))
                throw new MonitorQueryException("start time can not be null!");

            if (isNull(endTime))
                throw new MonitorQueryException("end time can not be null!");

            Date startDate = sdf.parse(beginTime);
            Date endDate = sdf.parse(endTime);
            Calendar c = Calendar.getInstance();
            c.setTime(endDate);   //设置日期
            c.add(Calendar.DATE, 1); //日期加1天
            endDate = c.getTime();
            return monitorRepository.findByCreateDBetween(startDate, endDate);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //查询动态监控数据 以天为单位
    public List<MonitorDay> getDayMonitors(String beginTime,String endTime){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            if (isNull(beginTime))
                throw new MonitorQueryException("start time can not be null!");

            if (isNull(endTime))
                throw new MonitorQueryException("end time can not be null!");

            Date startDate = sdf.parse(beginTime);
            Date endDate = sdf.parse(endTime);
            return monitorDayRepository.findByDayBetween(startDate, endDate);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //查询动态监控数据 以月为单位
    public List<MonitorMonth> getMonthMonitors(String beginTime,String endTime){
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            if (isNull(beginTime))
                throw new MonitorQueryException("start time can not be null!");

            if (isNull(endTime))
                throw new MonitorQueryException("end time can not be null!");

            String begin = beginTime.substring(0,6);
            begin = begin + "01";
            String end = endTime.substring(0,6);
            end = end + "01";

            Date startDate = sdf.parse(begin);
            Date endDate = sdf.parse(end);
            Calendar c = Calendar.getInstance();
            c.setTime(endDate);   //设置日期
            c.add(Calendar.DATE, 1); //日期加1天
            endDate = c.getTime();
            return monitorMonthRepository.findByMonthBetween(startDate, endDate);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //查询动态监控数据 以周为单位
    public List<MonitorWeek> getWeekMonitors(String beginTime,String endTime){
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            if (isNull(beginTime))
                throw new MonitorQueryException("start time can not be null!");

            if (isNull(endTime))
                throw new MonitorQueryException("end time can not be null!");

            Date startDate = sdf.parse(beginTime);
            Date endDate = sdf.parse(endTime);

            Calendar startC = Calendar.getInstance();
            startC.setTime(startDate);   //设置日期
            startC.add(Calendar.DATE, -6); //日期减6天
            startDate = startC.getTime();

            Calendar endC = Calendar.getInstance();
            endC.setTime(endDate);   //设置日期
            endC.add(Calendar.DATE, 1); //日期加1天
            endDate = endC.getTime();
            return monitorWeekRepository.findByWeekBetween(startDate, endDate);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private boolean isNull(String str) {
        return ("null".equalsIgnoreCase(str) || str == null|| "".equals(str));
    }

}
