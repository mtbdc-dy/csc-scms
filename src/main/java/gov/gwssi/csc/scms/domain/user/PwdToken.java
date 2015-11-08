package gov.gwssi.csc.scms.domain.user;

/**
 * Created by tianj on 2015/9/13.
 */
public class PwdToken {
    private String userId;
    private String fullName;
    private String password;
    private String userType;
    private String identity;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PwdToken(String userId, String fullName, String password, String userType, String identity) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.userType = userType;
        this.identity = identity;
    }
}
