package gov.gwssi.csc.scms.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.user.Node;
import org.junit.Test;

import java.util.List;

/**
 * Created by Lei on 2015/5/9.
 * node相关测试类
 */
public class NodeServiceTest extends UnitTestBase {

    private NodeService nodeService;

    private final static String beforePath = "   ";

    @Override
    public void before() {
        super.before();
        nodeService = getBean(NodeService.class);
    }

    @Test
    public void getNodeTreeTest() throws JsonProcessingException {
        List<Node> root = nodeService.getNodeTree();
        System.out.println(new ObjectMapper().writeValueAsString(root));
        printTree(root, "");
    }

    private void printTree(Node root, String path) {
        System.out.print(path);
        System.out.print("|--");
        System.out.println(root.getNode() + "(" + root.getNodeLevel() + ")");
    }

    private void printTree(List<Node> nodes, String path) {
        if (nodes == null || nodes.size() == 0)
            return;
        for (Node node : nodes) {
            printTree(node, path);
            printTree(node.getChildren(), path + beforePath);
        }
    }

}
