package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.dao.BaseDAO;
//import gov.gwssi.csc.scms.domain.StudentWzs;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.StudentRepository;
import gov.gwssi.csc.scms.service.BaseService;
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
public class StudentService extends BaseService {

    @Autowired
    @Qualifier("studentRepository")
    private StudentRepository studentRepository;
//    private Data

    public Student save (Student student){
        return studentRepository.save(student);
    }

    public List<Student> getStuInfoList(){
        List<Student> studentList = new ArrayList<Student>();
        String sql="select  t.nature_places, t.project_type_name, t.project_name, t.continent_name," +
                "t.country_name,t.passport_name from SCMS_BASIC_INFO t ";
        System.out.println("super.baseDAO============"+super.baseDAO);
        studentList = super.getBaseDao().queryListBySql(sql);
//        for (Student stu : studentRepository.findAll()){
//            studentList.add(stu);
//        }

        return studentList;
    }

}
