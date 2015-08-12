package gov.gwssi.csc.scms.service.remind;

import gov.gwssi.csc.scms.domain.remind.Remind;
import gov.gwssi.csc.scms.repository.remind.RemindRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianj on 2015/8/12.
 */
@Service("remindService")
public class RemindService extends BaseService {
    @Autowired
    @Qualifier("remindRepository")
    private RemindRepository remindRepository;

    public List<Remind> getReminds(){
        List<Remind> reminds=new ArrayList<Remind>();
        Iterator<Remind> iterator=remindRepository.findAll().iterator();
        while(iterator.hasNext()){
            Remind remind=iterator.next();
            reminds.add(remind);
        }
        return reminds;
    }
}
