package gov.gwssi.csc.scms.domain.user;

/**
 * Created by tianj on 2015/9/13.
 */
public class UserToken {
    private String userId;
    private String userType;
    private Node node;
    private Role role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserToken(String userId, String userType, Node node, Role role) {
        this.userId = userId;
        this.userType = userType;
        this.node = node;
        this.role = role;
    }
}
