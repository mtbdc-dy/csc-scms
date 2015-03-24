package gov.gwssi.csc.scms.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;

/**
 * Created by WangZishi on 3/20/2015.
 */
@Entity
public class User {

    @Autowired
    private ApplicationContext ctx;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    private String userName;
    private String userPassword;
    private String email;


    public Long getUserId() {
        return userId;
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
        return "User [userId=" + userId
                + ", userName=" + userName
                + ", userPassword=" + userPassword
                + ", email=" + email + "]";
    }
}
