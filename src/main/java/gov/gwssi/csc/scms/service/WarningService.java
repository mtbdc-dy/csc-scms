package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.domain.Student;
import gov.gwssi.csc.scms.domain.Warning;
import gov.gwssi.csc.scms.repository.WarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WangZishi on 3/26/2015.
 */
@Service("warningService")
public class WarningService {
    @Autowired
    private WarningRepository warningRepositiry;

    @Transactional
    public Warning warn(Student student){
        Warning warning = new Warning();

        warning.setReasonToAdd("too ugly!");
        warning.setAddedTime("today");

        warning.setStudent(student);

        return warningRepositiry.save(warning);
    }

    public List<Warning> getWarningList(){
        return warningRepositiry.findAll();
    }
}
