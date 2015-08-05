package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.student.RelatedAddressRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("relatedAddressService")
public class RelatedAddressService extends BaseService {
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    @Qualifier("relatedAddressRepository")
    private RelatedAddressRepository relatedAddressRepository;

    public RelatedAddress getRelatedAddressById(String id) {
        return relatedAddressRepository.findOne(id);
    }

    public Iterable saveRelatedAddress(List<RelatedAddress> relatedAddresses) {
        for (RelatedAddress relatedAddress : relatedAddresses)
            relatedAddress.setId(getBaseDao().getIdBySequence("SEQ_RELATED_ADDRESS"));
        return relatedAddressRepository.save(relatedAddresses);
    }

    public RelatedAddress updateRelatedAddress(RelatedAddress relatedAddress) {
        relatedAddress.setStudent(getRelatedAddressById(relatedAddress.getId()).getStudent());
        return relatedAddressRepository.save(relatedAddress);
    }

    public List<RelatedAddress> updateRelatedAddress(List<RelatedAddress> relatedAddresses) {
        List<RelatedAddress> list = new ArrayList<RelatedAddress>();
        for (RelatedAddress relatedAddress : relatedAddresses)
            list.add(updateRelatedAddress(relatedAddress));
        return list;
    }

    public List<RelatedAddress> getRelatedAddressByStudentId(String studentId) {
        return relatedAddressRepository.findByStudentId(studentId);
    }

    @Transactional
    public RelatedAddress saveAddress(RelatedAddress address, List<OperationLog> operationLogs) throws Exception {
        operationLogService.saveOperationLog(operationLogs);
        address.setId(getBaseDao().getIdBySequence("SEQ_RELATED_ADDRESS"));
        return relatedAddressRepository.save(address);
    }

    //删除
    public RelatedAddress deleteAddressById(Student student, User user, String addressId) {
        RelatedAddress address = getRelatedAddressById(addressId);
        if (address == null)
            return null;
        //记录日志
        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        OperationLog operationLog = new OperationLog();

        operationLog.setOptType("3");
        operationLog.setModule("在校生学籍管理");
        operationLog.setModuleId("BM003");
        operationLog.setStudentId(student.getId());
        operationLog.setCscId(student.getCscId());
        operationLog.setPassportName(student.getBasicInfo().getPassportName());
        String addressJsonStr = "{\"id\":\"" + address.getId() + "\",\"type\":\"" + address.getType() + "\",\"nature\":\"" + address.getNature() + "\",\"addressOrName\":\"" + address.getAddressOrName()
                + "\",\"phone\":\"" + address.getPhone() + "\",\"fax\":\"" + address.getFax() + "\",\"email\":\"" + address.getEmail() + "\",\"remark\":\"" + address.getRemark() + "\",\"createBy\":" + address.getCreateBy()
                + "\",\"createDate\":\"" + address.getCreateDate() + "\",\"updateBy\":\"" + address.getUpdateBy() + "\",\"updateDate\":\"" + address.getUpdateDate() + "\"}";
        operationLog.setBefore(addressJsonStr);
        operationLog.setAfter("");
        operationLog.setColumnCH("");
        operationLog.setColumnEN("");
        operationLog.setTableEN("scms_related_address");
        operationLog.setTableCH("学生相关地址表");
        operationLog.setNodeId(user.getNode().getNodeId());
        operationLog.setNodeType(user.getNode().getNodeType());
        operationLog.setCreateBy(user.getUserId());
        operationLog.setCreateD(new java.util.Date());

        operationLogs.add(operationLog);
        operationLogService.saveOperationLog(operationLogs);
        relatedAddressRepository.delete(address);
        address.setStudent(null);
        return address;
    }

    public RelatedAddress editAddress(RelatedAddress address, List<OperationLog> operationLogs) throws Exception {
        operationLogService.saveOperationLog(operationLogs);
        return relatedAddressRepository.save(address);
    }
}
