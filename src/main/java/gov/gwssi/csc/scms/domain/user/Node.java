package gov.gwssi.csc.scms.domain.user;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Murray on 2015/4/30.
 */
@Entity
@Table(name = "PUB_NODE")
public class Node {

    public final static String ENABLED = "1";

    public final static String UNENABLED = "0";

    public final static String ROOT_LEVEL = "1";

    /**
     * 节点代码
     */
    @Id
    private String nodeId;
    /**
     * 节点名称
     */
    private String node;
    /**
     * 节点英文名称
     */
    private String nodeEN;
    /**
     * 节点类型：AD基金委；AE省市；AF高等院校；AG派遣机构；AH洲；AI国家；AJ驻外使（领）馆教育处（组）。
     */
    private String nodeType;
    /**
     * 节点级别：0、根节点；1、一级；2、二级；3、三级；4、四级
     */
    private String nodeLevel;
    /**
     * 上级节点
     */
    @ManyToOne()
    @JoinColumn(name = "parentId")
    private Node parent;
    /**
     * 联系人姓名
     */
    private String contacts;
    /**
     * 联系人电话
     */
    private String phone;
    /**
     * 联系人地址
     */
    private String address;
    /**
     * 联系人邮箱
     */
    private String email;
    /**
     * 院校归属
     */
    private String collegeHome;
    /**
     * 是否有效
     */
    private String enabled;
    /**
     * 子节点
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Where(clause = "enabled = '1'")
    private List<Node> children;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public static String getENABLE() {
        return ENABLED;
    }

    public static String getUNENABLE() {
        return UNENABLED;
    }

    public static String getROOT_LEVEL() {
        return ROOT_LEVEL;
    }

    public String getNodeEN() {
        return nodeEN;
    }

    public void setNodeEN(String nodeEN) {
        this.nodeEN = nodeEN;
    }

    public String getCollegeHome() {
        return collegeHome;
    }

    public void setCollegeHome(String collegeHome) {
        this.collegeHome = collegeHome;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
