package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.repository.user.ProjectRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lei on 2015/5/5.
 * 预留功能，暂时未使用
 */
@Service("projectService")
public class ProjectService extends BaseService {

    @Autowired
    ProjectRepository rightRepository;

    public Project getProjectByProjectIdAndEnabled(String projectId, String enabled) {
        return rightRepository.findProjectByProjectIdAndEnabled(projectId, enabled);
    }

    public void getChildren(List<Project> projects) {
        if (projects != null && projects.size() > 0) {
            for (int i = 0; i < projects.size(); i++) {
                getChildren(projects.get(i).getChildren());
            }

        }
    }

    public void setParentNull(List<Project> projects) {
        for (Project project : projects) {
            Project parent = project.getParent();
            while (parent != null) {
                parent.setChildren(null);
                parent = parent.getParent();
            }
        }
    }

}
