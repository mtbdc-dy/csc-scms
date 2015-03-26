package gov.gwssi.csc.scms.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by ynh2_000 on 3/23/2015.
 */
@Service
public class UserService extends BaseService {

    public int getCurrentMaxUserId() {
        try {
            baseDao.save("123");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;

    }


}
