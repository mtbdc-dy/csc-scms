package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lei on 2015/5/5.
 */
public interface ProjectRepository extends CrudRepository<Project,String> {

    Project findProjectByProjectIdAndEnabled(String Project, String enabled);

}
