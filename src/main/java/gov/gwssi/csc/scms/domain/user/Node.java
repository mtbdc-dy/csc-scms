package gov.gwssi.csc.scms.domain.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Murray on 2015/4/30.
 */
@Entity
@Table(name = "PUB_NODE")
public class Node {
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
     * 节点类型：1基金委；2驻外使（领）馆教育处（组）；3高等院校
     */
    private String nodeType;
    /**
     * 节点级别
     */
    private String nodeLevel;
    /**
     * 上级节点
     */
    private String parentId;
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
     * 是否有效
     */
    private String enable;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
