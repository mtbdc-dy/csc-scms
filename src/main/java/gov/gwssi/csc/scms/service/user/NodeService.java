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

    public final static String ENABLE = "1";

    public final static String UNENABLE = "0";

    public final static String ROOT_LEVEL = "1";

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private UserService userService;

    public Node getNodeByNodeIdAndEnable(String nodeId, String enable) {
        return nodeRepository.findNodeByNodeIdAndEnable(nodeId, enable);
    }

    public List<Node> getNodesByEnable(String enable) {
        return nodeRepository.findNodeByEnable(enable);
    }

    public List<Node> getNodeTree() {
        List<Node> root = getRootNode();
        return root;
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

    public Node enableNode(String nodeId) throws NoSuchNodeException, NodeBeingUsedException {
        Node node = getNodeByNodeIdAndEnable(nodeId, ENABLE);
        if (node == null)
            throw new NoSuchNodeException();

        if (ENABLE.equals(node.getEnable())) {
            List<User> users = userService.getUsersByNode(node);
            if (users == null || users.size() == 0) {
                node.setEnable(UNENABLE);
            } else
                throw new NodeBeingUsedException();
        } else {
            node.setEnable(ENABLE);
        }
        return saveNode(node);
    }

    private List<Node> getRootNode() {
        return nodeRepository.findNodeByNodeLevelAndEnable(ROOT_LEVEL, ENABLE);
    }
}
