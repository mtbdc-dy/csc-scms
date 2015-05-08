package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.Node;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Lei on 2015/5/5.
 */
public interface NodeRepository extends CrudRepository<Node, String> {

    List<Node> findNodeByEnable(String enable);
}
