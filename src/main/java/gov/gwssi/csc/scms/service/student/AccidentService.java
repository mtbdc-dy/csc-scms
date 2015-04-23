package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.Accident;
import gov.gwssi.csc.scms.repository.student.AccidentRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("accidentService")
public class AccidentService extends BaseService {

    @Autowired
    @Qualifier("accidentRepository")
    private AccidentRepository accidentRepository;

    public Accident getAccidentById(Long id) {
        return accidentRepository.findOne(id);
    }

    public List<Accident> getAccidentByStudentId(Long studentId) {
        return accidentRepository.findByStudentId(studentId);
    }


    public Accident updateAccident(Accident accident) {
        Accident accidentTemp = getAccidentById(accident.getId());
        copyFiledValue(Accident.class, accident, accidentTemp);
        return saveAccident(accidentTemp);
    }

    public List<Accident> updateAccident(List<Accident> accidents) {
        List<Accident> list = new ArrayList<Accident>();
        for (Accident accident : accidents) {
            list.add(updateAccident(accident));
        }
        return list;
    }

    public Accident saveAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    public Iterable saveAccidents(List<Accident> accidents) {
        return accidentRepository.save(accidents);
    }

}
