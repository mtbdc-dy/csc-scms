package gov.gwssi.csc.scms.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;

/**
 * Created by WangZishi on 3/20/2015.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;

    private String userName;
    private String userPassword;

    public User(){}
    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }


    public long getUserId() {
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

    @Override
    public String toString(){
        return String.format(
                "Customer[userId=%d, userName='%s', userPassword='%s']",
                userId, userName, userPassword);
    }
}
