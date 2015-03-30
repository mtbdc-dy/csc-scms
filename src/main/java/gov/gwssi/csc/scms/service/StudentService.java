package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.domain.StudentWzs;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    public List<Student> getStuInfoList(){
        List<Student> studentList = new ArrayList<Student>();

        for (Student stu : studentRepository.findAll()){
            studentList.add(stu);
        }

        return studentList;
    }

}
