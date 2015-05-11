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

    public Accident getAccidentById(String id) {
        return accidentRepository.findOne(id);
    }

    public List<Accident> getAccidentByStudentId(String studentId) {
        return accidentRepository.findByStudentId(studentId);
    }


    public Accident updateAccident(Accident accident) {
        accident.setStudent(getAccidentById(accident.getId()).getStudent());
        return saveAccident(accident);
    }

    public List<Accident> updateAccident(List<Accident> accidents) {
        List<Accident> list = new ArrayList<Accident>();
        for (Accident accident : accidents) {
            list.add(updateAccident(accident));
        }
        return list;
    }

    public Accident saveAccident(Accident accident) {
        accident.setId(getBaseDao().getIdBySequence("SEQ_ACCIDENT"));
        return accidentRepository.save(accident);
    }

    public Iterable saveAccidents(List<Accident> accidents) {
        return accidentRepository.save(accidents);
    }

}
