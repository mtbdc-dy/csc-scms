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
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> findAllStudent(){

        List<Student> studentList = new ArrayList<Student>();

        for (Student stu : studentRepository.findAll()){
            studentList.add(stu);
        }

        return studentList;
    }

//    @Transactional
//    public Student createNewStudent(String cscNumber, String certificateNumber, String name, String gender){
//        Student student = new Student();
//        student.setCscNumber(cscNumber);
//        student.setCertificateNumber(certificateNumber);
//        student.setName(name);
//        student.setGender(gender);
//        return studentRepository.save(student);
//    }
//
//    @Transactional
//    public Student createNewStudent(Student student){
//        return studentRepository.save(student);
//    }
//
//    public Student findStudentByCscNumber(String cscNumber){
//        return studentRepository.findByCscNumber(cscNumber);
//    }
//
//    public List<Student> findStudentsByGender(String gender){
//        return studentRepository.findByGenderLike(gender);
//    }
}
