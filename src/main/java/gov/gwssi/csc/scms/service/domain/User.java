package gov.gwssi.csc.scms.service.domain;

/**
 * Created by WangZishi on 3/20/2015.
 */
public class User {
    private int userid;
    private String userName;
    private String userPassword;
    private String email;

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "User [userid=" + userid
                + ", userName=" + userName
                + ", userPassword=" + userPassword
                + ", email=" + email + "]";
    }
}
