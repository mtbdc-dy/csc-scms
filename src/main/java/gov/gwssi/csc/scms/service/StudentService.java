package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.domain.Student;
import gov.gwssi.csc.scms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Service("studentService")
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student createNewStudent(String cscNumber, String certificateNumber, String name, String gender){
        Student student = new Student();
        student.setCscNumber(cscNumber);
        student.setCertificateNumber(certificateNumber);
        student.setName(name);
        student.setGender(gender);
        return studentRepository.save(student);
    }

    public Student findStudentByCscNumber(String cscNumber){
        return studentRepository.findByCscNumber(cscNumber);
    }

    public List<Student> findStudentsByGender(String gender){
        return studentRepository.findByGenderLike(gender);
    }
}
