package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.repository.user.NodeRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lei on 2015/5/5.
 */
public class NodeService extends BaseService {

    @Autowired
    private NodeRepository nodeRepository;

    public Node getNodeByNodeId(String nodeId) {
        return nodeRepository.findOne(nodeId);
    }

    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }

    public Node addNode(Node node) {
        //node.setNodeId();
        return saveNode(node);
    }

    public Node updateNode(Node node) {
        return saveNode(node);
    }

    public Node enableNode(String nodeId) throws NoSuchNodeException {
        Node node = getNodeByNodeId(nodeId);
        if (node == null)
            throw new NoSuchNodeException();
        if (node.getEnable() == "1") {
            node.setEnable("0");
        } else {
            node.setEnable("1");
        }
        return saveNode(node);
    }
}
