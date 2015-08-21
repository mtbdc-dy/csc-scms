package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.Accident;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.student.AccidentRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
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
    private OperationLogService operationLogService;
    @Autowired
    @Qualifier("accidentRepository")
    private AccidentRepository accidentRepository;
    @Autowired
    private BaseDAO baseDAO;

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

    //新增突发事件并记录日志
    public Accident saveAccidentAndLog(Accident accident, List<OperationLog> operationLogs) throws Exception {
        operationLogService.saveOperationLog(operationLogs);
        accident.setId(getBaseDao().getIdBySequence("SEQ_ACCIDENT"));
        return accidentRepository.save(accident);
    }

    //删除
    public Accident deleteAccidentById(Student student, User user, String accidentId) {
        Accident accident = getAccidentById(accidentId);
        if (accident == null)
            return null;
        //记录日志
        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        OperationLog operationLog = new OperationLog();

        operationLog.setOptType("3");
        operationLog.setModule("在校生学籍管理");
        operationLog.setModuleId("BG003");
        operationLog.setStudentId(student.getId());
        operationLog.setCscId(student.getCscId());
        operationLog.setPassportName(student.getBasicInfo().getPassportName());
        String before = accident.getResponsibilityType() + "/" + baseDAO.getNameCHByTranslateId(accident.getType()) + "/" + accident.getReason() + "/" + accident.getHappenTime() + "/" + accident.getHappenAddress() + "/" + baseDAO.getNameCHByTranslateId(accident.getState()) + "/" + accident.getSummary() + "/" + accident.getCreateDate();
        operationLog.setBefore(before);
        operationLog.setAfter("");
        operationLog.setColumnCH("");
        operationLog.setColumnEN("");
        operationLog.setTableEN("scms_accident");
        operationLog.setTableCH("学生突发事件表");
        operationLog.setNodeId(user.getNode().getNodeId());
        operationLog.setNodeType(user.getNode().getNodeType());
        operationLog.setCreateBy(user.getUserId());
        operationLog.setCreateD(new java.util.Date());

        operationLogs.add(operationLog);
        operationLogService.saveOperationLog(operationLogs);
        accidentRepository.delete(accident);
        accident.setStudent(null);
        return accident;
    }

    public Accident editAccident(Accident accident, List<OperationLog> operationLogs) throws Exception {
        operationLogService.saveOperationLog(operationLogs);
        return accidentRepository.save(accident);
    }

}
