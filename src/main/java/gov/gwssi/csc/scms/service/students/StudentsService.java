package gov.gwssi.csc.scms.service.students;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    public Page<Student> getStudentsByFilter(Filter filter) {
        Specification<Student> specA = filterIsLike(filter);
//        Specification<Student> specB = userIs(user);
        return studentRepository.findAll(where(specA), new PageRequest(0, 20));
    }
    /*
    * add by gc20150810
    * 根据page,size参数查询student分页结果对象
    * */
    public Page<Student> getStudentsPageByFilter(Filter filter,Integer page,Integer size) {
        Specification<Student> specA = filterIsLike(filter);
//        Specification<Student> specB = userIs(user);
        return studentRepository.findAll(where(specA), new PageRequest(page, size));
    }


}
