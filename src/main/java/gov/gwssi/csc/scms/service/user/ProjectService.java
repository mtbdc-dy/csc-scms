package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.repository.user.ProjectRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lei on 2015/5/5.
 */
@Service("projectService")
public class ProjectService extends BaseService {

    @Autowired
    ProjectRepository rightRepository;

    public Project getRightByRightId(String rightId) {
        return rightRepository.findOne(rightId);
    }

    public Project saveRight(Project project) {
        return rightRepository.save(project);
    }

    public Project addRight(Project project) {
        project.setParentId(getBaseDao().getDicIdByClassType(project.getType()));
        return saveRight(project);
    }

}
