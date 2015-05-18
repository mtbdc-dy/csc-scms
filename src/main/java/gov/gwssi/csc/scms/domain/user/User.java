package gov.gwssi.csc.scms.domain.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Murray on 2015/4/30.
 * 系统用户类
 */
@Entity
@Table(name = "PUB_USER")
public class User {

    public final static String ENABLE = "1";

    public final static String UNENABLE = "0";
    /**
     * 用户名
     */
    @Id
    private String userId;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 角色
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLEID", nullable = false)
    private Role role;
    /**
     * 节点
     */
    @ManyToOne(targetEntity = Node.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "NODEID", nullable = false)
    private Node node;
    /**
     * 用户类型：1 基金委，2学校
     */
    private String userType;
    /**
     * 提示问题
     */
    private String question;
    /**
     * 问题答案
     */
    private String answer;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @Column(name = "CREATED")
    private Date createDate;
    /**
     * 有效标识：0无效 1有效
     */
    private String enable;
    /**
     * 用户权限
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PUB_USER_PROJECT", joinColumns = {@JoinColumn(name = "USERID")},
            inverseJoinColumns = {@JoinColumn(name = "PROJECTID")})
    private List<Project> projects = new ArrayList<Project>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRoll(Role role) {
        this.role = role;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
