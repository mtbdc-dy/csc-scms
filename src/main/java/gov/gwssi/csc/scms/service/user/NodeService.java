package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.NodeRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Node getNodeByNodeIdAndEnable(String nodeId, String enabled) {
        return nodeRepository.findNodeByNodeIdAndEnabled(nodeId, enabled);
    }

    @Transactional
    public Node getNodeWithoutEnable(String nodeId) {
        return nodeRepository.findOne(nodeId);
    }


    @Transactional
    public List<Node> getNodeTree() {
        List<Node> root = getRootNode();
//        setParentNull(root);
        return root;
    }

    @Transactional
    public Node saveNode(Node node) {
        node = nodeRepository.save(node);
//        node.setChildren(null);
//        node.setParent(null);
        return node;
    }

    @Transactional
    public Node addNode(Node node, User user) throws NoSuchNodeException {
        Node parent = getNodeWithoutEnable(node.getNodeId());
        if (parent == null) {
            throw new NoSuchNodeException("cannot find the parent of the node:" + node.getParent().getNodeId());
        }
        if (!Node.ENABLED.equalsIgnoreCase(parent.getEnabled())) {
            throw new NoSuchNodeException("the parent of the node is not enabled:" + node.getParent().getNodeId());
        }
//        node.setNodeId(getBaseDao().getDicIdByClassType(node.getNodeType()));
        if ("P".equals(node.getNodeType())) {
            node.setNodeId(node.getNode());
            node.setNode(getBaseDao().getNameCHByRegionId(node.getNode()));
        } else if ("S".equals(node.getNodeType())) {
            node.setNodeId(node.getNode());
            node.setNode(getBaseDao().getUnivByUnivId(node.getNode()));
        } else {
            node.setNodeId(getBaseDao().getDicIdByClassType(node.getNodeType()));
        }
        node.setParent(parent);
        return saveNode(node);
    }

    @Transactional
    public Node updateNode(Node node, User user) throws NoSuchNodeException {
        Node node1 = getNodeWithoutEnable(node.getNodeId());
        if (node1 == null)
            throw new NoSuchNodeException("cannot find node by nodeId:" + node.getNodeId());
        node.setParent(node1.getParent());
        node.setChildren(node1.getChildren());
        return saveNode(node);
    }

    @Transactional
    public Node deleteNodeByNodeId(String nodeId, User user) throws NoSuchNodeException, NodeBeingUsedException {
        Node node = getNodeByNodeIdAndEnable(nodeId, Node.ENABLED);
        if (node == null)
            throw new NoSuchNodeException("cannot find node by nodeId:" + nodeId);
        return unEnableNode(node);
    }

    @Transactional
    private Node unEnableNode(Node node) throws NodeBeingUsedException {
        List<User> users = userService.getUsersByNode(node);
        List<Node> childrenNode = node.getChildren();
        if ((users == null || users.size() == 0) && (childrenNode == null || childrenNode.size() == 0)) {
            node.setEnabled(Node.UNENABLED);
            return saveNode(node);
        } else
            throw new NodeBeingUsedException("some user using the node:" + node.getNodeId());
    }

    @Transactional
    private List<Node> getRootNode() {
        List<Node> nodes = nodeRepository.findNodeByNodeLevelAndEnabled(Node.ROOT_LEVEL, Node.ENABLED);
        getChildren(nodes);
        return nodes;
    }

    @Transactional
    public void getChildren(List<Node> nodes) {
        if (nodes != null && nodes.size() > 0) {
            for (int i = 0; i < nodes.size(); i++) {
                getChildren(nodes.get(i).getChildren());
            }

        }
    }

    public void setParentNull(List<Node> nodes) {
        if (nodes != null && nodes.size() > 0) {
            setNullByField(nodes, "parent", Node.class);
            for (Node node : nodes) {
                setParentNull(node.getChildren());
            }
        }
    }
}
