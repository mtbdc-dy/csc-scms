package gov.gwssi.csc.scms.service.students;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.student.StudentRepository;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.Expression;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by wang Zishi on 15/8/7.
 * 学生资源相关服务
 */
@Service(value = "studentsService")
public class StudentsService extends StudentSpecs {

    @Autowired
    @Qualifier("studentRepository")
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    public Page<Student> getStudentsByFilter(Filter filter) {
        Specification<Student> specA = filterIsLike(filter);
//        Specification<Student> specB = userIs(user);
        return studentRepository.findAll(where(specA), new PageRequest(0, 20));
    }

    /*
    * add by gc20150810
    * 根据page,size参数查询student分页结果对象
    * */
    public Page<Student> getStudentsPageByFilter(Filter filter, Integer page, Integer size) {
        Specification<Student> specA = filterIsLike(filter);
//        Specification<Student> specB = userIs(user);
        return studentRepository.findAll(where(specA), new PageRequest(page, size));
    }

    @Transactional
    public Page<Student> getStudentsPageByFilter(Filter filter, Integer page, Integer size, String mode, String header) {

        try {
            User user = userService.getUserByJWT(header);
            Specification<Student> specA = filterIsLike(filter, mode);
            Specification<Student> specB = userIs(user, mode);

            if ("freshregister".equals(mode)) {
                return studentRepository.findAll(where(specA).and(isFreshRegister(user)).and(specB), new PageRequest(page, size, Sort.Direction.ASC, "cscId"));
            } else if ("oldregister".equals(mode)) {
                return studentRepository.findAll(where(specA).and(isOldRegister(user)).and(specB), new PageRequest(page, size, Sort.Direction.ASC, "cscId"));
            } else if ("schoolstudent".equals(mode)) {
                return studentRepository.findAll(where(specA).and(isSchoolStudent()).and(specB), new PageRequest(page, size, Sort.Direction.ASC, "cscId"));
            } else if ("leavestudent".equals(mode) || "alumnus".equals(mode)) {
                return studentRepository.findAll(where(specA).and(isLeaveStudent()).and(specB), new PageRequest(page, size, Sort.Direction.ASC, "cscId"));
            } else if ("leaveChina".equals(mode)) {
                return studentRepository.findAll(where(specA).and(isLeaveChina()).and(specB), new PageRequest(page, size, Sort.Direction.ASC, "cscId"));
            } else {
                return studentRepository.findAll(where(specA).and(specB), new PageRequest(page, size, Sort.Direction.ASC, "cscId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String[] getStudentsAllByFilter(Filter filter, String mode, String header) {

        List<Student> studentsAll=null;
        try {
            User user = userService.getUserByJWT(header);
            Specification<Student> specA = filterIsLike(filter, mode);
            Specification<Student> specB = userIs(user, mode);

            if ("freshregister".equals(mode)) {
                studentsAll = studentRepository.findAll(where(specA).and(isFreshRegister(user)).and(specB));
            }else if ("oldregister".equals(mode)) {
                studentsAll = studentRepository.findAll(where(specA).and(isOldRegister(user)).and(specB));
            }else if ("export".equals(mode)){
                studentsAll = studentRepository.findAll(where(specA).and(specB));
            }else if("schoolstudent".equals(mode)){
                studentsAll = studentRepository.findAll(where(specA).and(isSchoolStudent()).and(specB));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String result[]=new String[studentsAll.size()];
        for(int i=0;i<studentsAll.size();i++){
            String cscId = studentsAll.get(i).getCscId();
            result[i] = cscId;
        }
        return result;
    }


}
