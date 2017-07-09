package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.student.NoSuchRelatedAddressException;
import gov.gwssi.csc.scms.service.student.RelatedAddressService;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjing on 2015/7/24.
 * 相关地址控制器
 */
@RestController
@RequestMapping(value = "/relatedaddress")
public class RelatedAddressController {
    @Autowired
    private RelatedAddressService relatedAddressService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    /**
     * 新增相关地址
     * @param studentId 学生id
     * @param addressJson 相关地址信息
     * @return
     */

    @RequestMapping(value = "/{studentId}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public RelatedAddress putRelatedAddress(@PathVariable(value = "studentId") String studentId,
                                            @RequestBody String addressJson) {
//        System.out.println("add address:" + addressJson);
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(addressJson, JsonBody.class);

            RelatedAddress relatedAddress = mapper.readValue(jbosy.getValue(), RelatedAddress.class);

            if (relatedAddress == null) {
                throw new NoSuchRelatedAddressException("cannot generate the address");
            }
            Student student = studentService.getStudentById(studentId);
            relatedAddress.setStudent(student);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            relatedAddress = relatedAddressService.saveAddress(relatedAddress, operationLogs);
            relatedAddress.setStudent(null);
            return relatedAddress;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除相关地址
     * @param header
     * @param studentId 学生id
     * @param addressId 相关地址id
     * @return
     */
    @RequestMapping(value = "/{studentId}/{addressId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public RelatedAddress deleteAddress(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header, @PathVariable(value = "studentId") String studentId, @PathVariable(value = "addressId") String addressId) {
        try {
            User user = userService.getUserByJWT(header);
            Student student = studentService.getStudentById(studentId);
            RelatedAddress address = relatedAddressService.deleteAddressById(student, user, addressId);
            if (address == null) {
                throw new NoSuchRelatedAddressException("cannot delete the address");
            }
            return address;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改相关地址
     * @param studentId 学生id
     * @param addressId 相关地址id
     * @param addressJson 相关地址信息
     * @return
     */
    @RequestMapping(value = "/{studentId}/{addressId}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public RelatedAddress editRelatedAddress(@PathVariable(value = "studentId") String studentId, @PathVariable(value = "addressId") String addressId,
                                             @RequestBody String addressJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(addressJson, JsonBody.class);

            RelatedAddress relatedAddress = mapper.readValue(jbosy.getValue(), RelatedAddress.class);

            if (relatedAddress == null) {
                throw new NoSuchRelatedAddressException("cannot edit the address");
            }

            Student student = studentService.getStudentById(studentId);
            relatedAddress.setStudent(student);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            relatedAddress = relatedAddressService.editAddress(relatedAddress, operationLogs);
            relatedAddress.setStudent(null);
            return relatedAddress;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
