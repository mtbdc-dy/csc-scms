package gov.gwssi.csc.scms.domain.user;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Murray on 2015/4/30.
 * 系统用户类
 */
@Entity
@Table(name = "PUB_USER")
public class User implements Serializable {

    public final static String ENABLE = "1";

    public final static String UNENABLE = "0";

    public final static String CSC_USER = "1";

    public final static String UNIVERSITY_USER = "2";

    @Id
    private String id;
    /**
     * 用户名
     */
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
     * 传真
     */
    private String fax;
    /**
     * 电话
     */
    private String phone;
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
     * 最后修改人
     */
    private String updateBy;
    /**
     * 最后修改时间
     */
    @Column(name = "UPDATED")
    private Date updateDate;
    /**
     * 有效标识：0无效 1有效
     */
    private String enable;
    /**
     * 用户权限
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PUB_USER_PROJECT", joinColumns = {@JoinColumn(name = "USERID", referencedColumnName = "USERID")},
            inverseJoinColumns = {@JoinColumn(name = "PROJECTID")})
    private List<Project> projects = new ArrayList<Project>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
