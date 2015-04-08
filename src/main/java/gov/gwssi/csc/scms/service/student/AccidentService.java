package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.Accident;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.AccidentRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("accidentService")
public class AccidentService extends BaseService {

    @Autowired
    private AccidentRepository accidentRepository;

    public Accident getAccidentById(String id) {
        return accidentRepository.findOne(id);
    }

    public Accident saveAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    public Iterable saveAccidents(List<Accident> accidents) {
        Iterable iterable = accidents;
        return accidentRepository.save(iterable);
    }

    public List<Accident> getAccidentsByStudent(Student student) {
        return accidentRepository.findByStudent(student.getCsc_id());
    }

}
