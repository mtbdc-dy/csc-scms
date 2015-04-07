package gov.gwssi.csc.scms.repository.student;

//import gov.gwssi.csc.scms.domain.StudentWzs;

import com.sun.xml.internal.bind.v2.model.core.ID;
import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository
@NamedQueries(
        {@NamedQuery(name = "a", query = ""),
                @NamedQuery(name = "", query = ""),
                @NamedQuery(name = "", query = ""),
                @NamedQuery(name = "", query = "")}
)
public interface StudentRepository extends CrudRepository<Student, String> {
    public List<Student> findBycscIdwehereOrderByAccident_Handle_statusDesc();
}
