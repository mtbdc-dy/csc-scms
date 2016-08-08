package gov.gwssi.csc.scms.service.remind;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.remind.Remind;
import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.remind.RemindRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by tianj on 2015/8/12.
 */
@Service("remindService")
public class RemindService extends BaseService {
    @Autowired
    @Qualifier("remindRepository")
    private RemindRepository remindRepository;
    @Autowired
    private BaseDAO baseDAO;
    @Autowired
    private UserService userService;

    public List<Remind> getReminds(){
        List<Remind> reminds=new ArrayList<Remind>();
        Iterator<Remind> iterator=remindRepository.findAll().iterator();
        while(iterator.hasNext()){
            Remind remind=iterator.next();
            reminds.add(remind);
        }
        return reminds;
    }
    @Transactional
    public Map<String,Integer> getReminds(String header){
        Map<String,Integer> result = new HashMap<String, Integer>();
        int abnormalUnSubmited = 0;
        int abnormalUnProcessed = 0;
        int abnormalUnApproved = 0;
        int ticketUnExport = 0;
        int scholarshipSubmited = 0;
        try {
            User user = userService.getUserByJWT(header);
            if ("Y0002".equals(user.getRole().getIdentity())) {    //主管用户
                List<Project> projects = user.getProjects();
                if (projects != null || projects.size() > 0) {
                    abnormalUnSubmited = baseDAO.getAbnormalZG(projects, "AS0006");
                    abnormalUnProcessed = baseDAO.getAbnormalZG(projects,"AS0008");
                    ticketUnExport = baseDAO.getTicketStatusNumZG(projects,"AT0002");
                    scholarshipSubmited = baseDAO.getScholarshipSubmited();
                }
            }else{
                abnormalUnApproved = baseDAO.getAbnormal("AS0007");
                ticketUnExport = baseDAO.getTicketStatusNum("AT0002");
                scholarshipSubmited = baseDAO.getScholarshipSubmited();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.put("abnormalUnSubmited",abnormalUnSubmited);
        result.put("abnormalUnProcessed",abnormalUnProcessed);
        result.put("abnormalUnApproved",abnormalUnApproved);
        result.put("ticketUnExport",ticketUnExport);
        result.put("scholarshipSubmited",scholarshipSubmited);
        return result;

    }
}
