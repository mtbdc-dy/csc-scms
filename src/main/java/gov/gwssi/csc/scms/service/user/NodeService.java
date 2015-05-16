package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.NodeRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lei on 2015/5/5.
 * 节点服务类
 */
@Service("nodeService")
public class NodeService extends BaseService {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private UserService userService;

    public Node getNodeByNodeIdAndEnable(String nodeId, String enabled) {
        return nodeRepository.findNodeByNodeIdAndEnabled(nodeId, enabled);
    }

    public Node getNodeWithEnable(String nodeId) {
        return nodeRepository.findOne(nodeId);
    }

    public List<Node> getNodesByEnable(String enabled) {
        return nodeRepository.findNodeByEnabled(enabled);
    }

    public List<Node> getNodeTree() {
        List<Node> root = getRootNode();
        setParentNull(root);
        return root;
    }

    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }

    public Node addNode(Node node) {
        node.setNodeId(getBaseDao().getDicIdByClassType(node.getNodeType()));
        return saveNode(node);
    }

    public Node updateNode(Node node) throws NoSuchNodeException {
        Node node1 = getNodeWithEnable(node.getNodeId());
        if (node1 == null)
            throw new NoSuchNodeException("cannot find node by nodeId:" + node.getNodeId());
        return saveNode(node);
    }

    public Node enableNode(String nodeId) throws NoSuchNodeException, NodeBeingUsedException {
        Node node = getNodeByNodeIdAndEnable(nodeId, Node.ENABLED);
        if (node == null)
            throw new NoSuchNodeException();

        if (Node.ENABLED.equals(node.getEnabled())) {
            List<User> users = userService.getUsersByNode(node);
            if (users == null || users.size() == 0) {
                node.setEnabled(Node.UNENABLED);
            } else
                throw new NodeBeingUsedException();
        } else {
            node.setEnabled(Node.ENABLED);
        }
        return saveNode(node);
    }

    private List<Node> getRootNode() {
        return nodeRepository.findNodeByNodeLevelAndEnabled(Node.ROOT_LEVEL, Node.ENABLED);
    }

    private void setParentNull(List<Node> nodes) {
        if (nodes != null && nodes.size() > 0) {
            setNullByField(nodes, "parent", Node.class);
            for (Node node : nodes) {
                setParentNull(node.getChildren());
            }
        }
    }
}
