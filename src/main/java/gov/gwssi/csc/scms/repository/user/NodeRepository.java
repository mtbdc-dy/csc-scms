package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.Node;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Lei on 2015/5/5.
 */
public interface NodeRepository extends CrudRepository<Node, String> {

    Node findNodeByNodeIdAndEnabled(String nodeId, String enabled);

    List<Node> findNodeByEnabled(String enabled);

    List<Node> findNodeByNodeLevelAndEnabled(String nodeLevel, String enabled);
}
