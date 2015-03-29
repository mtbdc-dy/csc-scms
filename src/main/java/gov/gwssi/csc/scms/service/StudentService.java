package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.domain.Student;
import gov.gwssi.csc.scms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//import java.util.List;

/**
 * Created by WangZishi on 3/25/2015.
 *
 */
@Service("studentService")
public class StudentService extends BaseService{

    @Autowired
    @Qualifier("studentRepository")
    private StudentRepository studentRepository;

    public Student save (Student student){
        return studentRepository.save(student);
    }

    public List<Student> findAllStudent(){
        StudentRepository studentRepository = super.getBean("studentRepository");

        List<Student> studentList = new ArrayList<Student>();

        for (Student stu : studentRepository.findAll()){
            studentList.add(stu);
        }

        return studentList;
    }

}
